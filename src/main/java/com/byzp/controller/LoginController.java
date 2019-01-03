package com.byzp.controller;

import com.byzp.pojo.ByLogin;
import com.byzp.pojo.ByStudent;
import com.byzp.pojo.ByTeacher;
import com.byzp.pojo.ByWeather;
import com.byzp.service.*;
import com.byzp.util.VaildateCodeUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class LoginController {

    @Autowired
    private ByLoginService byLoginService;

    @Autowired
    private ByStudentService byStudentService;

    @Autowired
    private ByCountService byCountService;

    @Autowired
    private ByTeacherService byTeacherService;

    @Autowired
    private ByWeatherService byWeatherService;

    /**
     * 登录
     */
    @RequestMapping("login")
    public String login(HttpServletRequest request, HttpServletResponse response,ByLogin byLogin,String PageVaildateCode,Model model){

        // -------------------------------------测试start-----------------------------

        String host = request.getRemoteAddr();

        System.out.println(host + " ********************* ");

        System.out.println((Object) request.getHeaders("cookie").nextElement());

        System.out.println( " ********************* ");

        Cookie[] cookie = request.getCookies();

        for(int i = 0; i < cookie.length; i++){

            System.out.println(cookie[i].getName() + " ***** " + cookie[i].getValue() + " ***** " + cookie[i].getDomain());

        }

        // -------------------------------------测试end----------------------------

        //统计数据库天气数据总条数
        int countweatherdata = byWeatherService.countweatherdata();

        System.out.println("数据总条数为：" + countweatherdata);

        if(countweatherdata > 110){

            //如果大于100条数据删除100条数据
            int clearweatherdata = byWeatherService.clearweatherdata();

            if(clearweatherdata > 0){

                System.out.println("******** 删除weather数据成功！********");

            }else{

                System.out.println("******** 删除weather数据失败！********");

            }

        }

        int selectloginusernamebyusername =  byLoginService.selectloginusernamebyusername(byLogin.getUsername());

        if(selectloginusernamebyusername == 1){

            //使用session存放登录信息
            HttpSession session = request.getSession();
            //调用service层方法完成查询，返回一个int类型数据
            int login =  byLoginService.login(byLogin);
            //从session中取出验证码
            String validateCode = (String) session.getAttribute("vaildatecode");
            //根据用户名查询登录身份
            ByLogin checkusername = byLoginService.checkloginidentity(byLogin);
            System.out.println("登录身份为： " +  checkusername.getIdentity());
            System.out.println("验证码是：  " + validateCode);
            //如果 == 1
            if(login == 1){

                //校验验证码
                if(validateCode.equalsIgnoreCase(PageVaildateCode)){

                    session.setAttribute("username",byLogin.getUsername());
                    session.setAttribute("identity",checkusername.getIdentity());

                    System.out.println("天气信息入库开始-------------------------");

                    dom4j();

                    System.out.println("天气信息入库结束-------------------------");

                    switch (checkusername.getIdentity()){

                        case "管理员" :
                            System.out.println("------进入管理员页面------");
                            return "homepage-admin";

                        case "老师" :
                            System.out.println("------进入老师页面------");
                            return "homepage-teacher";

                        case "学生" :
                            System.out.println("------进入学生管理员页面------");
                            return "homepage-student";

                    }

                }else {

                    model.addAttribute("codewaring", "验证码输入有误*");

                    return "forward:login.jsp";

                }

            }else{

                model.addAttribute("passwordwaring","密码输入有误*");

                //视图解析器解析返回值跳转页面
                return "forward:login.jsp";

            }

        }

        model.addAttribute("usernamewaring","用户名不存在*");

        try {

            InetAddress inetAddress = InetAddress.getLocalHost();

            String ip = inetAddress.getHostAddress().toString();

            String hostname = inetAddress.getHostName().toString();

            System.out.println("Ip为：【" + ip + "】" + " ------ " + "主机名为：【" + hostname + "】");

        } catch (UnknownHostException e) {

            e.printStackTrace();

        }

        //视图解析器解析返回值跳转页面
        return "forward:login.jsp";

    }

    /**
     *退出
     */
    @RequestMapping("exitlogin")
    public String exitlogin(){

        //请求转发到login页面
        return "forward:login.jsp";

    }

    /**
     *查询全部用户
     */
    @ResponseBody
    @RequestMapping("selectall")
    public List<ByLogin> selectall(Model model){
        //调用service方法查询全部用户
        List<ByLogin> selectall = byLoginService.selectall();
        //返回json格式数据
        return selectall;

    }

    /**
     *注册
     */
    @RequestMapping(value="insertuser")
    public String insertuser(ByStudent byStudent, MultipartFile pictureFile, HttpServletRequest request, ByTeacher byTeacher){

            //本地路径
            String localpath = "D:\\ideawork\\byzp\\src\\main\\webapp\\images";

            //uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            System.out.println("uuid为： " + uuid);
            //获得文件后缀名
            String suffixName = pictureFile.getOriginalFilename().
                    substring(pictureFile.getOriginalFilename().indexOf("."),pictureFile.getOriginalFilename().length());
            System.out.println("文件后缀名为： " + suffixName);
            //文件名字
            String filename = uuid + suffixName;
            System.out.println("新文件名为： " + filename);
            //图片上传路径
            String url = request.getSession().getServletContext().getRealPath("/");
            System.out.println("上传路径为： " + url);
            //以绝对路径保存重名命后的图片
            File newfile = new File(url + "images/" + filename);

            try {
                pictureFile.transferTo(newfile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*File localnewfile = new File(localpath + "/" + filename);

            try {
                pictureFile.transferTo(localnewfile);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            byStudent.setImage("/images/" + filename);

            System.out.println("身份为:【" + byStudent.getIdentity() + "】");

            if(byStudent.getIdentity().equals("学生")){

                int result = byStudentService.insertuser(byStudent);
                //result > 0表示添加成功，添加到by_student表
                if(result > 0){

                    System.out.println("------添加成功！-------");

                }else{

                    System.out.println("------添加失败！-------");

                }

                //添加student_number到count表
                int insertstudentnumber = byCountService.insertstudentnumber(String.valueOf(byStudent.getIdentity_number()));
                if(insertstudentnumber == 1){

                    System.out.println("添加userename成功！【" + byStudent.getIdentity_number() + "】");

                }else{

                    System.out.println("------添加username失败！-------");

                }

            }else{

                int teacherresult =  byTeacherService.insertteacherinfo(byStudent);

                //teacherresult > 0表示添加成功，添加到by_teacher表
                if(teacherresult > 0){

                    System.out.println("------添加by_teacher成功！-------");

                }else{

                    System.out.println("------添加by_teache失败！-------");

                }

            }

            //往by_login表添加数据
            int insertbyloginuserresult = byLoginService.insertbyloginuser(byStudent);
            //insertbyloginuserresult > 表示添加成功
            if(insertbyloginuserresult > 0){

                System.out.println("------添加byloginuser成功！-------");

            }else{

                System.out.println("------添加byloginuser失败！-------");

            }

        //跳转页面
        return "forward:WEB-INF/pages/homepage-admin.jsp";

    }

    /**
     *密码修改
     */
    @RequestMapping("updatepassword")
    public String updatepassword(HttpServletRequest request, HttpServletResponse response,String password){
        //创建session对象
        HttpSession session = request.getSession();
        //从session取出username的值
        String username = (String) session.getAttribute("username");

        String identity = (String) session.getAttribute("identity");

        System.out.println("---------------------------  " + username);
        //调用service方法，完成修改
        int updatepassword = byLoginService.updatepassword(username,password);
        //判断是否修改成功
        if(updatepassword > 0){

            System.out.println("------修改成功！-------");

        }else{

            System.out.println("------修改失败！-------");

        }

        switch (identity){

            case "管理员":
                return "forward:WEB-INF/pages/homepage-admin.jsp";
            case "老师":
                return "forward:WEB-INF/pages/homepage-teacher.jsp";
            case "学生":
                return "forward:WEB-INF/pages/homepage-student.jsp";

        }

        return "forward:err404.html";

    }

    /**
     *验证码
     */
    @RequestMapping("changevaildatecode")
    public void changevaildatecode(HttpServletRequest request,HttpServletResponse response){
        //创建session对象
        HttpSession session = request.getSession();
        //实例化验证码生成工具
        VaildateCodeUtil vaildateCodeUtil = new VaildateCodeUtil(160,40,4,20);
        //存入session
        session.setAttribute("vaildatecode",vaildateCodeUtil.getCode());

        try {
            vaildateCodeUtil.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @ResponseBody
    @RequestMapping("selectusername")
    public int selectusername(String username){

       int selectusername = byLoginService.selectusername(username);

        return selectusername;
    }

    @RequestMapping("test")
    public String test(Model model){

        model.addAttribute("test","test---------------------------");

        return "test";
    }

    @RequestMapping("deleteloginuserbyid")
    public String deleteloginuserbyid(int id){

        int deleteloginuserbyid =  byLoginService.deleteloginuserbyid(id);

        if(deleteloginuserbyid > 1){

            System.out.println("删除成功！id为：【" + id + "】");

        }else{

            System.out.println("删除失败！");

        }

      return "forward:WEB-INF/pages/homepage-admin.jsp";

    }

    /**
     *根据id删除用户
     */
    @RequestMapping("deleteuserbyid")
    @ResponseBody
    public void deleteuserbyid(int id){

        int deleteuserbyid = byLoginService.deleteuserbyid(id);

        if(deleteuserbyid > 0){

            System.out.println("删除成功，用户id为：【" + id +"】");

        }else{

            System.out.println("删除失败！");

        }

    }
    /**
     * 根据id查询用户信息
     */
    @RequestMapping("selectuserbyid")
    @ResponseBody
    public ByLogin selectuserbyid(int id){

        ByLogin selectuserbyid = byLoginService.selectuserbyid(id);

        return selectuserbyid;
    }
    /**
     * 根据id修改用户信息
     */
    @RequestMapping("updateuserbyid")
    public String updateuserbyid(ByLogin byLogin){

        int updateuserbyid = byLoginService.updateuserbyid(byLogin);

        if(updateuserbyid > 0){

            System.out.println("根据id修改用户信息成功！id为：【" + byLogin.getId() +"】");

        }else{

            System.out.println("根据id修改用户信息失败！");

        }

        return "forward:WEB-INF/pages/homepage-admin.jsp";
    }

    /**
     * 关闭修改窗口
     */
    @RequestMapping("closewindow")
    public String closewindow(){

        return "forward:WEB-INF/pages/homepage-admin.jsp";

    }

    /**
     * 查询identity
     */
    @RequestMapping("selectidentity")
    @ResponseBody
    public List<ByLogin> selectidentity(){

        List<ByLogin> selectidentity = byLoginService.selectidentity();

        return selectidentity;

    }

    /**
     * 查询登录信息根据identity字段
     */
    @RequestMapping("selectloginbyidentity")
    @ResponseBody
    public List<ByLogin> selectloginbyidentity(String identity){

        List<ByLogin> selectloginbyidentity = byLoginService.selectloginbyidentity(identity);

        return selectloginbyidentity;

    }

    /**
     * dom4j解析并且数据入库
     */
   /* @RequestMapping("dom4j")
    @ResponseBody*/
    public void dom4j(){

        System.out.println("天气信息入库");

       try{

           URL url = new URL("http://flash.weather.com.cn/wmaps/xml/shanxi.xml");

           URLConnection urlConnection = url.openConnection();

           InputStream inputStream = urlConnection.getInputStream();

           InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");

           BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

           String len = null;

           while((len = (bufferedReader.readLine())) != null){

               System.out.println(len);

           }

           if (bufferedReader != null){

               bufferedReader.close();

           }else if(inputStreamReader != null){

               inputStreamReader.close();

           }else if(inputStream != null){

               inputStream.close();

           }

           System.out.println("写入成功！");

           System.out.println(" ************************  获取系统时间  Start******************************** ");

           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");

           Date date = new Date();

           String time =  simpleDateFormat.format(date.getTime());

           System.out.println("当前系统时间为：【" + time + "】");

           String inserttime = time.substring(time.indexOf(time.charAt(0)),time.lastIndexOf(time.charAt(13)));

           System.out.println("inserttime为：【" + inserttime + "】");

           System.out.println(" ************************  获取系统时间  End  ******************************** ");

           List<ByWeather> selectweatherbyinserttime = byWeatherService.selectweatherbyinserttime(inserttime);

           System.out.println(selectweatherbyinserttime.size() + " -----------selectweatherbyinserttime----------------- ");

           if(selectweatherbyinserttime.size() == 0){

               //dom4j解析
               SAXReader saxReader = new SAXReader(); //使用SAXReader方式读取XML文件

               Document document = saxReader.read(url); //加载XML配置文件，得到Document对象

               Element elementroot = document.getRootElement(); //获得根节点

               System.out.println("当前节点名称： " + elementroot.getName() + " ------ " + "当前节点内容： " + elementroot.getText() + " ****** ");

               //单个遍历
           /* List<Attribute> listAttr=elementroot.attributes(); //当前节点的所有属性的list
            for(Attribute attr:listAttr){   //遍历当前节点的所有属性
                String name=attr.getName(); //属性名称
                String value=attr.getValue();   //属性的值
                System.out.println("属性名称："+name+"属性值："+value);
            }*/

               //全部遍历
               List<Element> listelement = elementroot.elements();

               ByWeather byWeather = null;

               int insertweather = 0;

               for(Element element : listelement){

                /*System.out.println(element.attribute("cityname") + " -------- " + element.attribute("cityname").getValue());*/

                   byWeather = new ByWeather(
                           new String(element.attribute("cityX").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("cityY").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("cityname").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("centername").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("fontColor").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("pyName").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("state1").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("state2").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("stateDetailed").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("tem1").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("tem2").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("temNow").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("windState").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("windDir").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("windPower").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("humidity").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("time").getValue().getBytes(),"utf-8"),
                           new String(element.attribute("url").getValue().getBytes(),"utf-8"),
                           time
                   );

                   insertweather = byWeatherService.insertweather(byWeather);

               }

               if(insertweather > 0){

                   System.out.println("weatherdata添加成功！");

               }else{

                   System.out.println("weatherdata添加失败！");

               }

           }

       }catch (Exception e){

           e.printStackTrace();

       }

           /* System.out.println(" ************************  获取网络时间  Start******************************** ");

            SimpleDateFormat simpleDateFormatdatel = null;

            try{

                URL urlntsc= new URL("http://www.ntsc.ac.cn"); //中国科学院国家授时中心

                URLConnection urlConnectionntsc = urlntsc.openConnection();

                urlConnectionntsc.connect();

                Long datel = urlConnectionntsc.getDate();

                Date internetdate = new Date(datel);

                SimpleDateFormat simpleDateFormatdatel = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

                simpleDateFormatdatel = new SimpleDateFormat("HH:mm");

                String internettime = simpleDateFormatdatel.format(internetdate);

                System.out.println("网络时间为：【" + internettime + "】");

                String newinternettime = internettime.substring(internettime.indexOf(internettime.charAt(0)),internettime.lastIndexOf(internettime.length()));

                System.out.println("newinternettime --- 【" + newinternettime + "】" );

            }catch(Exception e){

                e.printStackTrace();

            }

            System.out.println(" ************************  获取网络时间  End  ******************************** ");

        }catch (Exception e){

            e.printStackTrace();

        }*/

        /*InputStream is = null;

        Document document = null;

        try {

            URL url = new URL("http://flash.weather.com.cn/wmaps/xml/shanxi.xml");

            URLConnection urlConnection = url.openConnection();

            is = urlConnection.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(is,"utf-8");

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String len;

           *//* char[] chars = new char[1024];*//*

            while((len = (bufferedReader.readLine())) != null){

                System.out.println(len);

            }

            if (inputStreamReader != null){

                inputStreamReader.close();

            }else if(is != null){

                is.close();

            }

            System.out.println("读取完毕！");

            SAXReader saxReader = new SAXReader();

            document = saxReader.read(url);

            Element element = document.getRootElement();

            System.out.println(element.getName() + element.getTextTrim());

            System.out.println(" ------------------------------------------------------------------ ");

            Iterator iterator = element.elementIterator();

            while(iterator.hasNext()){

                Element e = (Element) iterator.next();

                System.out.println(e);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }*/

        /*   **********************************************  io流  ********************************************   */
        //io流test
        // FileInputStream是读取原始字节的图像数据流。读取字符流，考虑使用FileReader。

        /*try {

            File file = new File("C:\\Users\\Administrator\\Desktop\\test.txt");

            InputStream inputStream = new FileInputStream(file);

            int i;

            while ((i = inputStream.read()) != -1){

                System.out.println("i=" + i);

                System.out.println("i= ------------ " + (char)i);

            }

            if(inputStream != null){

                inputStream.close();

            }

        }catch (Exception e){

            e.printStackTrace();

        }*/

        /*try {

            File file = new File("C:\\Users\\Administrator\\Desktop\\test2.txt");

            InputStream inputStream = new FileInputStream(file);

            int i;

            byte[] bytes = new byte[1024];

            while ((i=(inputStream.read(bytes))) != -1){

                for(int j = 0; j < i; j++){

                    System.out.print((char) bytes[j]);

                }

            }

            if(inputStream == null){

                inputStream.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/

       //FileOutputStream
        /*try {

            File file = new File("C:\\Users\\Administrator\\Desktop\\test2.txt");

            if(!file.exists()){

                file.getParentFile().mkdirs();

                file.createNewFile();

            }

            OutputStream outputStream = new FileOutputStream(file);

            String str = "java io流写入---------------------------------------------------------------------------";

            byte[] bytes = str.getBytes();

            outputStream.write(bytes);

            outputStream.flush();

            System.out.println("文件写入成功！" + bytes.length ) ;

            if (outputStream != null){

                outputStream.close();

            }

        }catch (Exception e){

            e.printStackTrace();

        }*/

        //文件复制
        /*try {

            File o = new File("C:\\Users\\Administrator\\Desktop\\test.txt");
            File t = new File("C:\\Users\\Administrator\\Desktop\\test2.txt");

            if(!t.exists()){

                t.getParentFile().mkdirs();

                t.createNewFile();

            }

            InputStream inputStream = new FileInputStream(o);//需要追加字符加true
            OutputStream outputStream = new FileOutputStream(t,true);//需要追加字符加true

            int len = 0;
            byte[] bytes = new byte[1024];

            while ((len = (inputStream.read(bytes))) != -1){

                outputStream.write(bytes,0,len);

            }

            outputStream.flush();
            //先开后关
            if(outputStream != null){

                outputStream.close();

            }else if (inputStream != null){

                inputStream.close();

            }

            System.out.println("文件复制成功" + len);

        }catch (Exception e){

            e.printStackTrace();

        }*/

        //BufferedInputStream
        //带缓冲区的输入流，默认缓冲区大小是8M，能够减少访问磁盘的次数，提高文件读取性能；
        /*try {

            File t = new File("C:\\Users\\Administrator\\Desktop\\test2.txt");

            OutputStream outputStream = new FileOutputStream(t,true);

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            String newstr = "//BufferedInputStream\n" +
                    "        //带缓冲区的输入流，默认缓冲区大小是8M，能够减少访问磁盘的次数，提高文件读取性能；";

            byte[] bytes = newstr.getBytes();

            bufferedOutputStream.write(bytes);

            bufferedOutputStream.flush();

            if(bufferedOutputStream != null){

                bufferedOutputStream.close();

            }

            System.out.println("文件写入成功");

        }catch (Exception e){

            e.printStackTrace();

        }*/

        //DataOutputStream

       /* 数据输入流允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型。应用程序可以使用数据输出流写入稍后由数据输入流读取的数据。 DataInputStream 对于多线程访问不一定是安全的。 线程安全是可选的，它由此类方法的使用者负责。

        构造方法： DataOutputStream( OutputStream outs)*/

        //ObjectInputStream ：对象输入流

       /* 特点 : 可以从输入流中读取java对象，而不需要每次读取一个字节，需要把InputStream包装到ObjectInputStream中，就可以从中读取对象，对象必须实现序列化，对象类实现Serializable接口
        例：（实现序列化）
        关闭流------>这里与其他不同，先关闭InputStream的，再关闭OutputStream */

       // 字符流
       //  InputStreamReader
        //  OutputStreamWriter
        /*try {

            Scanner sc = new Scanner(System.in);

            File file = new File("C:\\Users\\Administrator\\Desktop\\test2.txt");

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");

           *//* System.out.println("输入内容：");

            String msg = sc.next();*//*

           String msg = "你好,这是OutputStreamWriter";

            outputStreamWriter.write(msg);

            outputStreamWriter.flush();

            System.out.println("写入成功！");

            InputStream inputStream = new FileInputStream(file);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");

            char[] chars = new char[1024];

            int len;

            while((len = (inputStreamReader.read(chars))) != -1){

                System.out.println(chars);

            }

            if(inputStreamReader != null){

                inputStreamReader.close();

            }else if(inputStream != null){

                inputStream.close();

            }else if(outputStreamWriter != null){

                outputStreamWriter.close();

            }

        }catch (Exception e){

            e.printStackTrace();

        }*/

        //FileReader
        //FileWriter
        /*与上面InputStreamReader 和 OutputStreamWriter没啥区别 */

        // BufferedReader（缓冲流）
        //BufferedWriter （缓冲流）

        /*try{

            File file = new File("C:\\Users\\Administrator\\Desktop\\test2.txt");

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            String str = "这是BufferedWriter1 ------------------------------------------------------- ";

            String str2 = "这是BufferedWriter2 ------------------------------------------------------- ";

            bufferedWriter.write(str);

            bufferedWriter.write(str2);

            bufferedWriter.flush();

            System.out.println("写入成功！");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String len = null;

            while((len = bufferedReader.readLine()) != null){

                System.out.println(len);

            }

        }catch(Exception e){

            e.printStackTrace();

        }*/

    }

}
