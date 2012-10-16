$(function () {

  function log(str) {
    if (typeof(console) !== "undefined") {
      console.log(str);
    }
  }

  // The search user will initiate the scroller once
  var scrolling = false;

  //
  $("#users").on("click", ".editable", function(e) {
    var name = $(this).parents(".user").attr("id");
    // We should somehow display a spinning wheel to show the user that the operation
    // is blocking
    
    $("<div></div>").load($(".jz").jzURL("Controller.getProfile"), "userName=" + name, function() {
    	$("#modal-container").html($(this).html());
        $("#modal").modal("show");
    });
    
    //
    e.stopPropagation();
    e.preventDefault();
  });

  $("#people").on("click", ".modal-footer a", function(e) {
    //
    var query = $("#profile-form").serialize();

    // We should somehow display a spinning wheel to show the user that the operation
    // is blocking
    $('.jz').jzAjax("Controller.setProfile()", {
      data: query,
      async: false,
      success: function(html) {
      }
    });

    //
    $("#modal").modal("hide");

    //
    e.preventDefault();
    e.stopPropagation();
  });

  //
  var searchUsers = function (append) {
  	append == append || false;
    var args = $("#users-search").serialize();
    if (append) {
      var offset = $(".user").size();
      args = args + "&offset=" + offset;
    }
    
    log("doing request " + args)
    $('.jz').jzAjax("Controller.findUsers()", {
    	data: args,
    	type: "get",
    	async: false,
    	dataType: "json",
    	success: function(resp) {
    		if (!append) {
          $("#users .checkable:not(.checked)").parents(".user").remove();
        }
    		$.each(resp, function(item) {
    			if(document.getElementById(item) == null) {
    				var html =
          		"<li id=" + item +" class='user'>" +
              "<a class='editable' href='#'><i class='icon-search icon-user'></i></a>" +
              "<a class='checkable' href='#'><i class='icon-search icon-empty'></i><i class='icon-search icon-plane'></i><i class='icon-search icon-magnet'></i></a>" +
              "<div class='display-name'>" + resp[item]["firstName"] + " " + resp[item]["lastName"] + "</div>";
              "</li>";
          		$(html).appendTo("#users");
    			}
    		});
    		
    		//
        if (!scrolling) {
          scrolling = true;
          scroller();
        }
    	}
    });
  };
  
  var searchGroups = function (name, userName) {
	var args = $("#groups-search").serialize();
    $('#groups').load($(".jz").jzURL("Controller.findGroups"), args, function () {
    })
  };

  //
  $("#users-search").on("keyup", "input[type=text]", function () {
    searchUsers();
  });

  //
  $("#groups-search").on("keyup", "input[type=text]", function () {
    searchGroups();
  });

  //
  var addUser = function() {
    $("<input name='userName' type='hidden'/>").appendTo("#groups-search").val($(this).attr("id"));
  };
  var removeUser = function() {
    $("#groups-search input[type=hidden][value=" + $(this).attr("id") + "]").remove();
  };

  $("#users").on("click", ".checkable:not(.checked)", function(e) {
    $(this).addClass("checked");
    $(this).parents(".user").addClass("selected").each(addUser);
    $("#users").find(".user.selected .checkable:not(.checked)").parents(".user").removeClass("selected").each(removeUser);
    searchGroups();
    e.stopPropagation();
    e.preventDefault();
  });
  $("#people").on("click", ".checkable.checked", function(e) {
    $(this).removeClass("checked");
    $(this).parents(".user").removeClass("selected").each(removeUser);
    searchGroups();
    e.stopPropagation();
    e.preventDefault();
  });

  //
  $("#people").on("click", ".user", function () {
    if ($("#users .checkable.checked").size() == 0)
    {
      $("#users .user.selected").removeClass("selected").each(removeUser);
      $(this).addClass("selected").each(addUser);
      searchGroups();
    }
  });

  $("#people").on("click", ".remove-membership,.add-membership", function(e) {
    var url = $(this).attr("ajax-url");
    // We should somehow display a spinning wheel to show the user that the operation
    // is blocking
    
    $("<div></div>").load(url, function() {
    	$('#groups').html($(this).html());
    });
    e.stopPropagation();
    e.preventDefault();
  });

  // Scroller defined before we call searchUsers below
  scroller = function() {
    $("#users").each(function() {
      var scrollBox= $(this).parents(".scrollbox").get(0);
      var scrolltop = scrollBox.scrollTop;
      var scrollheight = scrollBox.scrollHeight;
      var windowheight = scrollBox.clientHeight;
       var scrolloffset = 20;
      if(scrolltop >= (scrollheight - (windowheight + scrolloffset))) {
        searchUsers(true);
      }
    });
    
    //TODO: This makes many requests
    //setTimeout("scroller();", 1500);
  };

  // Init UI
  searchUsers();
  searchGroups();
});