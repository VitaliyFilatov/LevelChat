var mid = 0;
$(document).ready(function() {
        $("#test").click(function(){	
        var mes = document.getElementById('message').value
        $.get("/LevelChat/ajaxadd",{mid});  
        });
    });
    $(document).everyTime(2000, 'refresh', function() {
    		$.get("/LevelChat/ajax",{mid}).done(function(data,status){
            var list = document.getElementById('list')
            for (i = 0; i < data.length; i++)
            {
              var li = document.createElement('LI')
              li.innerHTML = data[i]
              list.appendChild(li)
            }
            mid+=data.length+1
        });
      });