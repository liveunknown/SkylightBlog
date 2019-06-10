$(function(){
    LoadFriendLink(9); // 加载所有友链
});

function LoadFriendLink(type) {
    $.ajax({
        url: "/getFriendLinkList",
        type: "GET",
        data:{isFamous:type},
        async: false,
        success: function (data) {
            console.log("成功了！");
            $("#tableType").html("友链列表");
            var content = '<thead>'+
                '                    <tr>'+
                '                        <th>ID</th>'+
                '                        <th>网站名称</th>'+
                '                        <th>网站链接</th>'+
                '                        <th>是否著名</th>'+
                '                    </tr>'+
                '                    </thead>'+
                '                    <tbody>';
            var id ;
            var blogger ;
            var url ;
            var isFamous ;

            for(var i in data)
            {
                id = data[i].id;
                blogger = data[i].blogger;
                url = data[i].url;
                isFamous = data[i].isFamous;

                content = content +'<tr>'+
                    '                        <td>'+id+'</td>'+
                    '                        <td>'+blogger+'</td>'+
                    '                        <td>'+url+'</td>'+
                    '                        <td>'+isFamous+'</td>'+
                    '<td><button class="btn btn-success btn-xs" onclick="loadModifyModal('+id+')" ><span class="glyphicon glyphicon-pencil"></span> 编辑</button>&#8194;'+
                    '<button class="btn btn-danger btn-xs" onclick="loadDeleteModal('+id+')" data-toggle="modal" data-target="#deleteModal"><span class="glyphicon glyphicon-trash"></span> 删除</button></td>'+
                    '                    </tr>';
                '                </div>';
                if(i==data.length)
                {
                    content = content + ' </tbody>';
                }
            }
            $("#table").html(content);
        }, error: function () {
            alert("友链加载错误");
        }
    });
}

function addFriendLink() {
        var blogger = $('#blogger').val();
        var url = $('#url').val();
        var isFamous = $('#isFamous').val();

        $.ajax({
            url: "/addFriendLink",
            type: "POST",
            data: {
                blogger : blogger,
                url : url,
                isFamous : isFamous
            },
            dataType: "json",
            success: function (data) {
                console.log(data);
                alert("添加成功~");
                document.location.reload();
            }, error: function () {
                alert("添加友链错误");
            }
        });

}

function deleteFriendLink(id) {
    $.ajax({
        type: "GET",
        url: "/deleteFriendLink",
        data: {id:id},
        success: function (data) {
            console.log(data);
            alert("删除友链成功");
            document.location.reload();
        }, error: function () {
            alert("删除友链失败！");
        }
    });
}

function modifyFriendLink(id) {
    var blogger = $('#modifiedBlogger').val();
    var url = $('#modifiedUrl').val();
    var isFamous = $('#modifiedIsFamous').val();
    isFamous = Number(isFamous);
    $.ajax({
        type: "POST",
        url: "/updateFriendLink",
        async: false,
        data: {
            id : id,
            blogger : blogger,
            url : url,
            isFamous : isFamous
        },
        success: function (data) {
            console.log(data);
            alert("更新友链成功~")
            Reload();
        }, error: function () {
            alert("更新友链失败");
        }
    });
}

function loadModal() {
    $('#myModal').modal('show');
    var Buttons = '<button type="button" class="btn btn-primary" onclick="addFriendLink()">确定</button>'+
                  '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>';
    $("#modalFooter").html(Buttons);
}

function Reload() {
    document.location.reload();
}

function loadModifyModal(id) {
    $('#modifyModal').modal('show');
    $('#history').val(id);
    var Buttons = '<button type="button" class="btn btn-primary" onclick="modifyFriendLink('+id+')">确定</button>'+
        '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>';
    $("#modifyModalFooter").html(Buttons);
}

function loadDeleteModal(id) {
    var Buttons =  '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'+
        '<button type="button" class="btn btn-primary" onclick="deleteFriendLink('+id+')">删除</button>';
    $("#deleteModalFooter").html(Buttons);
}
