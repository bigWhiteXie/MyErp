<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.js"></script>

    <script type="text/javascript">
    $(document).ready(function(){
      $(".click").click(function(){
      $(".tip").fadeIn(200);
      });

      $(".tiptop a").click(function(){
      $(".tip").fadeOut(200);
    });

      $(".sure").click(function(){
      $(".tip").fadeOut(100);
    });

      $(".cancel").click(function(){
      $(".tip").fadeOut(100);
    });

    });
    </script>
    <script>
    $(()=>{
      getData()
    })
      //删除部门
      function deleteDept(deptno){
        $.ajax(
          {
            url:"/MyErp/DeptServlet?method=delDept",
            method:"get",
            data:{deptno:deptno},
            success:(res)=>{
              eval(res);
              if(msg == 'ok'){
                window.location.reload()
              }else{
                alert("删除失败")
              }
            }
          }
        )
      }
      //获得部门列表
      function getData(index,size){
        if(index === undefined || size === undefined){
          index = 1;
          size = 5;
        }
        $.ajax(
          {
            url:"/MyErp/DeptServlet?method=findPage",
            data:{index:index,size:size},
            method:"post",
            dataType:"json",
            success:(res)=>{
              console.log(res)
              $("#tb").empty()
              $(res.list).each((index,item)=>{
                $("#tb").append(
                  "<tr><td><input name='select' type='checkbox' value="+item.deptno+" /></td><td>"+item.deptno+"</td><td>"+item.deptname+"</td><td>"+item.location+"</td><td><a href='javascript:void(0)' onclick='updateDept("+item.deptno+")'  class='tablelink click'>修改</a> &nbsp;&nbsp;&nbsp;&nbsp;  <a href='javascript:void(0)' class='tablelink' onclick='deleteDept("+item.deptno+")'> 删除</a></td> </tr>"
                  )
              })
            }
          }
        )
      }
      
      function updateDept(deptno){
        $(".tip").fadeIn(200);
        $(".sure").click(()=>{
          $.ajax({
            url:"/MyErp/DeptServlet?method=updateDept",
            data:{deptno:deptno},
            method:'get',
            success:(res)=>{
              eval(res);
              if(msg == 'ok'){
                window.location.href = 'deptUpdate.jsp'
              }else{
                alert("修改失败，请稍后再试")
              }
            }
          })
        })

      }
    </script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">人事管理</a></li>
    <li><a href="#">部门管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
      
   <div class="formtitle1"><span>部门列表</span></div>
   
    <table class="tablelist" >
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>编号<i class="sort"><img src="../images/px.gif" /></i></th>
        <th>部门名称</th>
        <th>办公地点</th>
        <th>操作</th>
        </tr>
        </thead>
        
        <tbody id="tb">
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>1</td>
        <td>总裁办</td>
        <td>501</td>
        <td><a href="deptUpdate.html" class="tablelink">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;  <a href="#" class="tablelink click"> 删除</a></td>
        </tr> 
        
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>2</td>
        <td>教学部</td>
        <td>302</td>
        <td> <a href="deptUpdate.html" class="tablelink">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;   <a href="#" class="tablelink click"> 删除</a></td>
        </tr>
        
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>3</td>
        <td>咨询部</td>
        <td>204</td>
        <td> <a href="deptUpdate.html" class="tablelink">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;   <a href="#" class="tablelink"> 删除</a></td>
        </tr>
        
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>4</td>
        <td>教务部</td>
        <td>303</td>
        <td>  <a href="deptUpdate.html" class="tablelink">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;   <a href="#" class="tablelink"> 删除</a></td>
        </tr>
        </tbody>
    </table>
    
       
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="../images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="judge" type="button"  class="sure" value="确认"  />&nbsp;
        <input name="judge" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
