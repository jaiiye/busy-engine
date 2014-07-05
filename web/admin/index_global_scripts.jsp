<%@ page contentType="text/html; charset=utf-8" language="java"  errorPage="" %>
       
<script> 
	function setVisibility(id, visibility) 
	{
		document.getElementById(id).style.display = visibility;
	}
	
	function toggleVisibility(id) 
	{
		if (document.getElementById(id).style.display == 'none')
		{
			document.getElementById(id).style.display = 'block';
		}
		else
		{
			document.getElementById(id).style.display = 'none';
		}
	}
</script>

<!-- BEGIN CORE PLUGINS -->        
    
    <!--[if lt IE 9]>
        <script src="../assets/global/plugins/respond.min.js"></script>
        <script src="../assets/global/plugins/excanvas.min.js"></script> 
    <![endif]-->
    
    <script src="../assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    
    <script src="../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->
