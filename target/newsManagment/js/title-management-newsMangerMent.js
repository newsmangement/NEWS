$(function(){
    getNewsType();
    getAllNews();
    jieAjax();
    $(".zhang").change(function(){
        jieAjax();
    })


    $('.J_search').click(function(){
        chaBufen();
    });


    /*
        点击收起，收起当前问题框
    */
    $(document).on('click','.J_retract',function(e){
        $(e.target).html("展开").addClass("J_edit").removeClass("J_retract");
        $(e.target).parents(".top").next().css("display","none");
    })

    /*
        点击展开，展开当前问题框
    */
    $(document).on('click','.J_edit',function(e){
        $(e.target).html("收起").addClass("J_retract").removeClass("J_edit");
        $(e.target).parents(".top").next().css("display","block");
    })


    function getNewsType(){
        var str1 = " 新闻类别：";
        $.ajax({
            type:'get',
            url:'http://localhost/news/getNewsType',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: { },
            dataType: "json",
            success: function (rs) {
                $('#newsType').empty();
                $.each(rs, function(index, item){
                    str1+='<p class="btn btn-success btn-radius-no margin-left-15" onclick="getNewsByType('+item.id+')" type="button">'+item.param_name+'</p>'
                });
                $('#newsType').append(str1);
            },
            error: function (message) {
                alert("请求发送失败。")
            }
        });
    }





    function getAllNews(){
        var zhang = $('.zhang').val();
        var jie = $('.jie').val();
        var str1 = "</a><tr></tr><th>序号</th><th>标题</th><th>发布时间</th><th>阅读量</th>><th>操作</th></tr>";
        var str2 = "";
        $.ajax({
            type:'get',
            url:'http://localhost/news/getAllNews',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {
                chapterId:zhang,
                sessionId:jie
            },
            dataType: "json",
            success: function (rs) {
                $('#newslist').empty();
                $.each(rs, function(index, item){
                    str1 +='<tr><td>'+item.id+'</td><td><p onclick="getNewsById('+item.id+')">'+item.title+'</p></td><td>'+item.time+'</td><td>'+item.viewsNum+'</td><td><button type="button" class="btn btn-primary">修改</button>&nbsp;&nbsp;<button type="button" class="btn btn-danger" onclick="deleteNews('+item.id+')">删除</button></td></tr>'

                });
                $('#newslist').append(str1);
            },
            error: function (message) {
                alert("请求发送失败。")
            }
        });
    }

})