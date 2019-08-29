$(function(){
    editormd("test-editormd", {
        width   : "100%",
        height  : 1000,
        syncScrolling : "single",
        path    : "editormd/lib/",
        //使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
        //saveHTMLToTextarea : true,
        imageUpload : true,
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : "/uploadImage",
    });

    var id = getQueryVariable("id");
    console.log("获取的文章ID: "+id);
    var edit = getQueryVariable("edit");
    console.log("获取的操作码: "+edit);

    LoadCategories();
    LoadLabels();
    if(edit=='false') {
        authorLinkBind();
    }
    // 判断是新建还是编辑
    if(edit=='true')
    {
        getArticleDetailById(id);
        $("#authorDiv").show();
        $("#urlDiv").show();
    }

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

function addArticle() {
    var title = $('#title').val();
    var author = $('#author').val();
    var url = $('#url').val();
    console.log("title: " +title);
    var summary = $('#summary').val();
    var categoryId = $('#category').val();
    console.log("categoryId: " +categoryId);
    var content = $('#content').val();
    var labels = $('#labels').val();
    console.log("labels: " +labels);
    var fd = new FormData();
    fd.append('title',title);
    fd.append('author',author);
    fd.append('url',url);
    fd.append('summary',summary);
    fd.append('categoryId',categoryId);
    fd.append('content',content);
    fd.append('labels',labels);
    //console.log(fd);
    $.ajax({
        url: "/addArticle",
        type: "POST",
        data: {
            title:title,
            summary:summary,
            categoryId:categoryId,
            content:content,
            labels:JSON.stringify(labels)
        },
        dataType: "json",
        success: function (data) {
            if(data == true) {
                alert("添加文章成功~");
                document.location.reload();
            }
            else{
                alert(data.msg);
                console.log("返回的信息是："+data.msg);
            }
        }, error: function () {
            alert("数据加载错误");
        }
    });
}

function addArticleByFormData() {
    var title = $('#title').val();
    var author = $('#author').val();
    var url = $('#url').val();
    var summary = $('#summary').val();
    var categoryId = $('#category').val();
    var isOriginal = $('#isOriginal').val();
    var content = $('#content').val();
    var labels = $('#labels').val();

    var fd = new FormData();
    fd.append('title',title);
    fd.append('author',author);
    fd.append('url',url);
    fd.append('summary',summary);
    fd.append('categoryId',categoryId);
    fd.append('isOriginal',isOriginal);
    fd.append('content',content);
    fd.append('labels',JSON.stringify(labels));

    $.ajax({
        url: "/addArticle",
        type: "POST",
        data:fd,
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (data) {
            if(data == true) {
                //alert("添加文章成功~");
                $('#modalContent').html("添加文章成功~");
                $('#myModal').modal('show');
                //document.location.reload();
            }
            else{
                alert(data.msg);
                console.log("返回的信息是："+data.msg);
            }

        }, error: function () {
            $('#modalContent').html("添加文章失败");
            $('#myModal').modal('show');
            alert("添加文章失败");
        }
    });
}

function LoadCategories() {
    $.ajax({
        url: "/categories",
        type: "GET",
        async: false,
        data: {},
        success: function (data) {
            var content = "";
            var id;
            var name;
            for(var i in data)
            {
                id = data[i].id;
                name = data[i].name;
                content = content +'<option value="'+id+'">'+name+'</option>';
            }
            $("#category").html(content);
        }, error: function () {
            alert("数据加载错误");
        }
    });
}

function LoadLabels() {
    $.ajax({
        url: "/labels",
        type: "Get",
        async: false,
        data: {},
        success: function (data) {
            var content = "";
            var id;
            var name;
            for(var i in data)
            {
                id = data[i].id;
                name = data[i].name;
                content = content + '<option value="'+id+'">'+name+'</option>';
            }
            $("#labels").html(content);
        }, error: function () {
            alert("数据加载错误");
        }
    });
}


function reloadPage() {
    document.location.reload();
}

function getArticleDetailById(id) {
    $.ajax({
        type: "GET",
        url: "/articleWrapDetail",
        async: false,
        data: {id:id},
        success: function (data) {
            var title = data.title;
            var author = data.author;
            var url = data.url;
            var summary = data.summary;
            var categoryId = data.categoryId;
            var isOriginal = data.isOriginal;
            var labels = new Array();
            var content = data.articleContent.content;
            //在这里将articleInfo和articleContent的id传给editArticle()
            var editButton = '<button type=\"button\" class=\"btn btn-info col-md-offset-5 col-md-2\" onclick=\"editArticle('+ data.articleInfoId+','+data.articleContent.id+')\">修改文章</button>'

            $("#category").selectpicker('val',categoryId);

            $("#isOriginal").selectpicker('val',isOriginal);

            for(var i=0;i<data.articleLabelList.length;i++)
            {
                labels[i] = data.articleLabelList[i].label.id;
            }

            $("#labels").selectpicker('val',labels);

            $("#title").val(title);
            $("#author").val(author);
            $("#url").val(url);
            $("#summary").val(summary);
            $("#content").html(content);
            $("#button-area").html(editButton);

        }, error: function () {
            alert("数据加载错误");
        }
    });
}

// 需要articleInfo和articleContent的id
function editArticle(articleId,contentId) {
    var title = $('#title').val();
    var author = $('#author').val();
    var url = $('#url').val();
    var summary = $('#summary').val();
    var categoryId = $('#category').val();
    var isOriginal = $('#isOriginal').val();
    var content = $('#content').val();

    var backButton = '<button type="button" class="btn btn-primary" id="reload" data-dismiss="modal" onclick="BackToManagePage()">确定</button>';
    $.ajax({
        type: "POST",
        url: "/updateArticle",
        async: false,
        data: {
            articleId:articleId,
            title:title,
            author:author,
            url:url,
            summary:summary,
            categoryId:categoryId,
            isOriginal:isOriginal,
            contentId:contentId,
            content:content
        },
        success: function (data) {
            if(data==true)
            {
                //alert("修改文章成功~");
                //Reload();
                $('#modalContent').html("修改文章成功~");
                $("#modalFooter").html(backButton);
                $('#myModal').modal('show');
            }
            else{
                alert(data.msg);
                console.log("返回的信息是："+data.msg);
            }
        }, error: function () {
            $('#modalContent').html("修改文章失败");
            $('#myModal').modal('show');
            alert("修改文章失败");
        }
    });

}

function BackToManagePage() {
    window.location.href="/admin/manageArticle.html";
}

function authorLinkBind() {
    $("#isOriginal").bind("change", function() {
        if(this.value == "1") {
            $("#authorDiv").hide();
            $("#urlDiv").hide();
        } else if(this.value == "0") {
            $("#authorDiv").show();
            $("#urlDiv").show();
        }
    })
}
