  //查询信息
  function table_find(){
    var name_f = $('#find_name').val();
    var sex_f = $('#find_sex option:selected').text();
    var age_f = $('#find_age').val();
    var ph_f = $('#find_ph').val();
    var id_f = $('#find_id').val();
    var data_f = {
      "userName":name_f,
      "userId":id_f,
      "gender":sex_f,
      "phoneNumber":ph_f,
      "age":age_f
    };
    //查询用ajax
   $.ajax({
   url:'/v1/info/find',
   type:'post',
   contentType: 'application/json;charset=utf-8',
   data:JSON.stringify(data_f),
   success:function(datas){
      var table=$("#all_data");
      table.empty();
      var td1;
      for(i=0;i<datas.length;i++)
      {
        td1+="<tr><td>"+datas[i].userName+"</td><td>"+datas[i].userId+"</td><td>"+datas[i].gender+"</td><td>"+datas[i].phoneNumber+"</td><td>"+datas[i].age+"</td><td><button class=\"btn btn-primary btn-sm edit\" onclick=\"show_edit_modal("+i+")\" id=edit_button_"+i+"\">修改</button>"+"</td>";
      }
      table.html(td1);
     },
    error:function(){
      $('#modal_title').html('警告');
      $('#modal_msg').html('查询失败，请检查网络情况');
      $('#modal_react').modal('show');
    }
   })
  }
      //修改、删除、增加
      //自动填入用户点击该行的信息
      function show_edit_modal(num) {
        $('#edit_name').val($("#students_find").find('tr').eq(num+1).find('td').eq(0).text());
        $('#edit_ph').val($("#students_find").find('tr').eq(num+1).find('td').eq(3).text());
        $('#edit_id').val($("#students_find").find('tr').eq(num+1).find('td').eq(1).text());
        $('#edit_age').val($("#students_find").find('tr').eq(num+1).find('td').eq(4).text());
        var sex = $("#students_find").find('tr').eq(num+1).find('td').eq(2).text();
        if(sex=="男")
        {
          $('#edit_sex').val('0');
        }else if(sex=="女"){
          $('#edit_sex').val('1');
        }
        $('#modal_edit').modal('show');
      };
      //自动填入用户点击该行的信息       
      function show_edit_modal_me() {
        $('#edit_name_me').val($("#main_name").html());
        $('#edit_ph_me').val($("#main_ph").html());
        $('#edit_id_me').val($("#main_id").html());
        $('#edit_age_me').val($("#main_age").html());
        var sex = $("#main_sex").html();
        if(sex=="男")
        {
          $('#edit_sex_me').val('0');
        }else if(sex=="女"){
          $('#edit_sex_me').val('1');
        }
        $('#modal_edit_myself').modal('show');
      };   
      //自动填入用户点击该行的信息
      function show_edit_modal_pass() {
        $('#edit_id_me_p').val($("#main_id").html());
        $('#modal_edit_pass').modal('show');
      };   
      //显示新增信息
      function show_modal_add() {
        $('#modal_add').modal('show');
      }; 
      //添加信息的ajax
      function data_add() {
             var name_a = $('#add_name').val();
             var sex_a = $('#add_sex').val();
             if(sex_a==1){
                sex_a="女";
             }else if(sex_a==0){
              sex_a="男";
             }
             var age_a = $('#add_age').val();
             var ph_a = $('#add_ph').val();
             var id_a = $('#add_id').val();
             var data_a ={
               "userName":name_a,
               "userId":id_a,
               "gender":sex_a,
               "phoneNumber":ph_a,
               "age":age_a
             };
             //添加用ajax
             $.ajax({
                  url:'/v1/auth/add',
                  data:JSON.stringify(data_a),
                  type:'post',
                  contentType: 'application/json;charset=utf-8',
                  success:function(datas){
                    if(datas.msg="success"){
                      $('#modal_title').html('通知');
                      $('#modal_msg').html('添加成功');
                      $('#modal_react').modal('show');
                    }else{
                      $('#modal_title').html('警告');
                      $('#modal_msg').html('添加失败，请联系管理员');
                      $('#modal_react').modal('show');
                    }
                  },
                  error:function(){
                    $('#modal_title').html('警告');
                    $('#modal_msg').html('添加失败，请检查网络情况');
                    $('#modal_react').modal('show');
                  }
              })
          
          };


      //修改信息的ajax
      function data_edit() {
             var name_n = $('#edit_name').val();
             var sex_n = $('#edit_sex').val();
             if(sex_n==1){
                sex_n="女";
             }else if(sex_n==0){
              sex_n="男";
             }
             var age_n = $('#edit_age').val();
             var ph_n = $('#edit_ph').val();
             var id_n = $('#edit_id').val();
             var data_n ={
               "userName":name_n,
               "userId":id_n,
               "gender":sex_n,
               "phoneNumber":ph_n,
               "age":age_n
             };
             //修改用ajax
             $.ajax({
                  url:'/v1/info/edit',
                  data:JSON.stringify(data_n),
                  type:'post',
                  contentType: 'application/json;charset=utf-8',
                  success:function(datas){
                    if(datas.msg="success"){
                      $('#modal_title').html('通知');
                      $('#modal_msg').html('修改成功');
                      $('#modal_react').modal('show');
                    }else{
                      $('#modal_title').html('警告');
                      $('#modal_msg').html('修改失败，请联系管理员');
                      $('#modal_react').modal('show');
                    }
                  },
                  error:function(){
                    $('#modal_title').html('警告');
                    $('#modal_msg').html('修改失败，请检查网络情况');
                    $('#modal_react').modal('show');
                  }
              })
          
          };
          //删除的ajax
      function data_del() {
             var id_d = $('#edit_id').val();
             var data_d ={
               "userId":id_d,
             };
             $.ajax({
                  url:'/v1/info/del',
                  type:'post',
                  data:JSON.stringify(data_d),
                  contentType: 'application/json;charset=utf-8',
                  success:function(datas){
                    if(datas.msg="success"){
                      $('#modal_title').html('通知');
                      $('#modal_msg').html('删除成功');
                      $('#modal_react').modal('show');
                    }else{
                      $('#modal_title').html('警告');
                      $('#modal_msg').html('删除失败，请联系管理员');
                      $('#modal_react').modal('show');
                    }
                  },
                  error:function(){
                    $('#modal_title').html('警告');
                    $('#modal_msg').html('删除失败，请检查网络情况');
                    $('#modal_react').modal('show');
                  }
              })
          
          };



  //进入页面加载个人中心信息
  function main_msg(){
    data_mian_top = {
      "see":"cancanword"
    }
    $.ajax({
      url:'/v1/info/currentUser',
      type:'POST',
      data:JSON.stringify(data_mian_top),
      contentType: 'application/json;charset=utf-8',
      success:function(datas){
           $('#top_name').html(datas.userName);
           $('#main_name').html(datas.userName);
           $('#main_gender').html(datas.gender);
           $('#main_id').html(datas.userId);
           $('#main_ph').html(datas.phoneNumber);
           $('#main_age').html(datas.age);
      },
    })
  };
  $(document).ready(main_msg());


  //进入页面修改个人中心信息
function edit_msg_me(){
    var name_m = $('#edit_name_me').val();
    var sex_m = $('#edit_sex_me option:selected').text();
    var age_m = $('#edit_age_me').val();
    var ph_m = $('#edit_ph_me').val();
    var id_m = $('#edit_id_me').val();
    var data_m ={
          "userName":name_m,
          "userId":id_m,
          "gender":sex_m,
          "phoneNumber":ph_m,
          "age":age_m,
    };
    $.ajax({
      url:'/v1/info/edit',
      type:'POST',
      data:JSON.stringify(data_m),
      contentType: 'application/json;charset=utf-8',
      success:function(datas){
        if(datas.msg="success"){
        main_msg();
        $('#modal_title').html('通知');
        $('#modal_msg').html('修改成功');
        $('#modal_react').modal('show');
        
        }else{
        $('#modal_title').html('警告');
        $('#modal_msg').html('修改失败，请联系管理员');
        $('#modal_react').modal('show');
        }
      },
      error:function(){
        $('#modal_title').html('警告');
        $('#modal_msg').html('修改失败，请检查网络情况');
        $('#modal_react').modal('show');
        }
     })
  };
  //修改密码
  function edit_pass(){
    var id_pass = $('#edit_id_me').val();
    var pass = $('#edit_pass').val();
    var data_pass ={
          "userId":id_pass,
          "pass":pass,
    };
    $.ajax({
      url:'/v1/info/edit',
      type:'POST',
      data:JSON.stringify(data_pass),
      contentType: 'application/json;charset=utf-8',
      success:function(datas){
        if(datas.msg="success"){
        $('#modal_title').html('通知');
        $('#modal_msg').html('修改成功');
        $('#modal_react').modal('show');
        main_msg();
        }else{
        $('#modal_title').html('警告');
        $('#modal_msg').html('修改失败，请联系管理员');
        $('#modal_react').modal('show');
        }
      },
      error:function(){
        $('#modal_title').html('警告');
        $('#modal_msg').html('修改失败，请检查网络情况');
        $('#modal_react').modal('show');
        }
     })
  };



  //查看选课的学生函数
  function class_find(num)
{
  var class_name = $('#class_find').find('tr').eq(num).find('td').eq(0).text();
  $('#class_name_choose').html(class_name);
  data_cT = {
    'courseName':class_name
  };
  $.ajax({
   url:'/v1/info/classT',
   type:'post',
   contentType: 'application/json;charset=utf-8',
   data:JSON.stringify(data_cT),
   success:function(datas){
      var table=$("#class_students_data");
      table.empty();
      var td1;
      for(i=0;i<datas.length;i++)
      {
        td1+="<tr><td>"+datas[i].userName+"</td><td>"+datas[i].userId+"</td><td><button class=\"btn btn-danger btn-sm edit\" onclick=\"class_del_student("+i+")\" >删除</button></td>";
      }
      table.html(td1);
      $('#modal_class_teacher').modal('show');
     },
    error:function(){
      $('#modal_title').html('警告');
      $('#modal_msg').html('查询失败，请检查网络情况');
      $('#modal_react').modal('show');
    }
   })
  };
  function class_find_n()
  {
    var class_name_n = $('#class_name_choose').html();
    data_cT = {
      'courseName':class_name_n
    };
    $.ajax({
     url:'/v1/info/classT',
     type:'post',
     contentType: 'application/json;charset=utf-8',
     data:JSON.stringify(data_cT),
     success:function(datas){
        var table=$("#class_students_data");
        table.empty();
        var td1;
        for(i=0;i<datas.length;i++)
        {
          td1+="<tr><td>"+datas[i].userName+"</td><td>"+datas[i].userId+"</td><td><button class=\"btn btn-danger btn-sm edit\" onclick=\"class_del_student("+i+")\" >删除</button></td>";
        }
        table.html(td1);
        $('#modal_class_teacher').modal('show');
       },
      error:function(){
        $('#modal_title').html('警告');
        $('#modal_msg').html('查询失败，请检查网络情况');
        $('#modal_react').modal('show');
      }
     })
    };
  //删除在课程中的学生
  function class_del_student(num){
  var stuid = $("#class_find_T").find('tr').eq(num+1).find('td').eq(1).text();
  var classname = $('#class_name_choose').html();
  data_d_S={
    'userId':stuid,
      'courseName':classname
  }
  $.ajax({
    url:'/v1/info/classDelStudent',
    type:'post',
    contentType:'application/json;charset=utf-8',
    data:JSON.stringify(data_d_S),
    success:function(datas){
      if(datas.msg=="success")
      {
        class_find_n();
      }else{
        $('#modal_title').html('警告');
        $('#modal_msg').html('删除失败，请联系管理员');
        $('#modal_react').modal('show');
      }
    },
    error:function(){
        $('#modal_title').html('警告');
        $('#modal_msg').html('删除失败，请检查网络情况');
        $('#modal_react').modal('show');
    }
  })
  }

  //展示学生选课
  function show_student_class(){
    var id = $('#main_id').html();
    var data_class_show ={
       'userId':id
    };
    var magic = ["","","","",""];
    var class_all = ["专业英语","操作系统","近世代数","普通地质学","信号与系统"];
    $.ajax({
      url:'v1/info/classShowStudent',
      type:'post',
      contentType:'application/json;charset=utf-8',
      data:JSON.stringify(data_class_show),
      success:function(datas){
        magic[0]=datas.专业英语;
        magic[1]=datas.操作系统;
        magic[2]=datas.近世代数;
        magic[3]=datas.普通地质学;
        magic[4]=datas.信号与系统;
        for(i=0;i<=4;i++)
        {
          if(magic[i]==false){
            var string1 = "<button class=\"btn btn-primary\" onclick=\"class_choose("+i+")\" >选课</button>"
            // var string2 = "#class_"+i;
            // $(string2).html(string1);
            $('#all_class_data').find('tr').eq(i).find('td').eq(1).html(string1);
          }else if(magic[i]==true){
            var string2 = "<button class=\"btn btn-danger\" onclick=\"class_del("+i+")\" >退课</button>"
            // var string2 = "#class_"+i;
            // $(string2).html(string);
            $('#all_class_data').find('tr').eq(i).find('td').eq(1).html(string2);
          }
        }
      }
    })
  };
  //学生选课
  function class_choose(num){
    var class_name = $('#class_choose_student').find('tr').eq(num+1).find('td').eq(0).text();
    var id = $('#main_id').html();
    var data_class_name ={
       'userId':id,
       'courseName':class_name
    } ;
    $.ajax({
      url:'v1/info/classChoose',
      type:'post',
      contentType:'application/json;charset=utf-8',
      data:JSON.stringify(data_class_name),
      success:function(datas){
        if(datas.msg=="success"){
        $('#modal_title').html('通知');
        $('#modal_msg').html('选课成功');
        $('#modal_react').modal('show');
        show_student_class();
        }else{
          $('#modal_title').html('警告');
        $('#modal_msg').html('选课失败，请联系管理员');
        $('#modal_react').modal('show');
        }
      },
      error:function(){
        $('#modal_title').html('警告');
        $('#modal_msg').html('选课失败，请检查网络情况');
        $('#modal_react').modal('show');
      }
    })
  };
  //学生退课
  function class_del(num){
    var class_name = $('#class_choose_student').find('tr').eq(num+1).find('td').eq(0).text();
    var id = $('#main_id').html();
    var data_class_name ={
       'userId':id,
       'courseName':class_name
    } ;
    $.ajax({
      url:'v1/info/classDel',
      type:'post',
      contentType:'application/json;charset=utf-8',
      data:JSON.stringify(data_class_name),
      success:function(datas){
        if(datas.msg=="success"){
        $('#modal_title').html('通知');
        $('#modal_msg').html('退课成功');
        $('#modal_react').modal('show');
        show_student_class();
        }else{
          $('#modal_title').html('警告');
        $('#modal_msg').html('退课失败，请联系管理员');
        $('#modal_react').modal('show');
        }
      },
      error:function(){
        $('#modal_title').html('警告');
        $('#modal_msg').html('退课失败，请检查网络情况');
        $('#modal_react').modal('show');
      }
    })
  }
  
  function logout()
  {
   $.ajax({
     async:false,
     type: 'POST',
     url: '/v1/auth/logout',
     contentType: 'application/json;charset=utf-8',
     data: '你是一个一个一个一个一个一个一个一个一个',
     complete:function(){
       $(location).attr('href', 'login.html');
     }
    });
  }