$(function(){
    var id = getQueryVariable("id");
    console.log("-----: "+id);
    getArticleById(id);
    editormd.markdownToHTML("content", {//注意：这里是上面DIV的id
        htmlDecode: "style,script,iframe",
        emoji: true,
        taskList: true,
        tex: true, // 默认不解析
        flowChart: true, // 默认不解析
        sequenceDiagram: true, // 默认不解析
        codeFold: true,
    });
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
        type: "GET",
        url: "/articleWrapDetail",
        async: false,
        data: {id:id},
        success: function (data) {
           var categories = "";
           var labels = "";
           console.log(data);
           $("#title").html(data.title);
           $("#here").val(data.articleContent.content);

           categories = '<span class="label label-success">原创</span>&#8194'+
                        '<span class="glyphicon glyphicon-time">&#8197;'+data.createBy+'&#8197;&#8197;</span>'+
                        '<span class="glyphicon glyphicon-user">&#8197;郁磊&#8197;&#8197;</span>'+
                        '<span class="glyphicon glyphicon-tag">&#8197;'+data.category.name+'</span>';
           $("#category").html(categories);

        }, error: function () {
            alert("数据加载错误");
        }
    });
}