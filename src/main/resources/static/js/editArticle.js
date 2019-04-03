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

    LoadCategories();
    LoadLabels();
});

function addArticle() {
    var title = $('#title').val();
    console.log("title: " +title);
    var summary = $('#summary').val();
    var categoryId = $('#category').val();
    console.log("categoryId: " +categoryId);
    var content = $('#content').val();
    var labels = $('#labels').val();
    console.log("labels: " +labels);
    var fd = new FormData();
    fd.append('title',title);
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
            console.log(data);
        }, error: function () {
            alert("数据加载错误");
        }
    });
}

function addArticleByFormData() {
    var title = $('#title').val();
    console.log("title: " +title);
    var summary = $('#summary').val();
    var categoryId = $('#category').val();
    console.log("categoryId: " +categoryId);
    var content = $('#content').val();
    var labels = $('#labels').val();
    console.log("labels: " +labels);
    var fd = new FormData();
    fd.append('title',title);
    fd.append('summary',summary);
    fd.append('categoryId',categoryId);
    fd.append('content',content);
    fd.append('labels',JSON.stringify(labels));
    console.log(fd);
    $.ajax({
        url: "/addArticle",
        type: "POST",
        data:fd,
        dataType: "json",
        contentType: false,
        processData: false,
        success: function (data) {
            console.log(data);
            $('#modalContent').html("添加文章成功~");
            $('#myModal').modal('show');
        }, error: function () {
            $('#modalContent').html("添加文章失败");
            $('#myModal').modal('show');
            alert("数据加载错误");
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
            console.log("成功了！");
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
            console.log("成功了！");
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