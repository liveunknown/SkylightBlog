
function login()
{
    $.ajax({
        type:"POST",
        url:"/login",
        data: $('#loginform').serialize(),

        success:function(data){
            console.log("成功了！");
            alert("!");
            console.log(data);
        },error:function(){
            alert("数据加载错误");
        }

    });



}