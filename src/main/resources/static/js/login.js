
function login()
{
    $.ajax({
        type:"POST",
        url:"/admin/login",
        data: $('#loginform').serialize(),

        success:function(data){
            console.log("成功了！");
            alert("!");
            if(data==true)
            {
            window.location.href = "/admin/admin.html";
            }
            console.log(data);
        },error:function(){
            alert("数据加载错误");
        }

    });



}