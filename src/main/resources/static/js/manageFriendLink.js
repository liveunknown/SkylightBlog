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
                if(data == true) {
                    alert("添加友链成功~");
                    document.location.reload();
                }
                else{
                    alert(data.msg);
                    $('#myModal').modal('hide');
                    console.log("返回的信息是："+data.msg);
                }
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
            if(data == true) {
                alert("删除友链成功~");
                document.location.reload();
            }
            else{
                alert(data.msg);
                $('#deleteModal').modal('hide');
                console.log("返回的信息是："+data.msg);
            }
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
            if(data==true)
            {
                alert("更新友链成功~");
                Reload();
            }
            else{
                alert(data.msg);
                $('#modifyModal').modal('hide');
                console.log("返回的信息是："+data.msg);
            }
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
    var Buttons = '<button type="button" class="btn btn-primary" onclick="deleteFriendLink('+id+')">删除</button>' +
        '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>';
    $("#deleteModalFooter").html(Buttons);
}
