$(function(){
    var id = getQueryVariable("id");
    alert("你好呀！");
    console.log("-----: "+id);
    getArticleById(id);

});

function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (false);
}


function getArticleById(id) {
    $.ajax({
        type: "POST",
        url: "/articleWrapDetail",
        data: {id:id},
        dataType: "json",
        success: function (data) {
           console.log(data);
        }
    });
}