$(function(){

    getNewsType();

// 富文本编辑器wangeditor
        var E = window.wangEditor;
        var editor1 = new E('#div1');
        editor1.create();


    function getNewsType(){
        var str1 = "<option value=\"\">请选择</option>";
        $.ajax({
            type:'get',
            url:'http://localhost/news/getNewsType',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: { },
            dataType: "json",
            success: function (rs) {
                $('#newsType').empty();
                $.each(rs, function(index, item){
                    str1+='<option value="'+item.id+'">'+item.param_name+'</option>'
                });
                $('#newsType').append(str1);
            },
            error: function (message) {
                alert("请求发送失败。")
            }
        });
    }

    function addNews(){
        var
            title = $('#newsTitle').val(),
            type= $("#newsType").val(),
            text =editor1.txt.html();
        var data={
            title:title,
            type:type,
            text:text
        };
        $.ajax({
            type:"post",
            url: "http://localhost/news/addNews",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: data,
            dataType: "json",
            success: function (rs) {
                alert('添加成功');
                window.location.href = "../html/title-management-list.html";
            },
            error: function (message) {
                alert('操作失败!');
            }
        });
    }
//向后台提交验证
    $(".J_save").click(function() {
        // if(filterFileImg('fileToUpload')){
        addNews();
        // }
    })

});

