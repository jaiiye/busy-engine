
var table = $('#sample_2');

/* Set tabletools buttons and button container */
$.extend(true, $.fn.DataTable.TableTools.classes, {
    "container": "btn-group tabletools-dropdown-on-portlet",
    "buttons": {
            "normal": "btn btn-sm default",
            "disabled": "btn btn-sm default disabled"
    },
    "collection": {
            "container": "DTTT_dropdown dropdown-menu tabletools-dropdown-menu"
    }
});


if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) 
{
    var tableToolsButtons = [{
            "sExtends": "print",
            "sButtonText": "Print",
            "sInfo": 'Print View!'
    }];
}
else 
{
    var tableToolsButtons = [{
            "sExtends": "copy",
            "sButtonText": "Copy"
    }, {
            "sExtends": "print",
            "sButtonText": "Print",
            "sInfo": 'Please press "CTRL+P" to print or "ESC" to quit'
    }, {
            "sExtends": "pdf",
            "sButtonText": "PDF"
    }, {
            "sExtends": "xls",
            "sButtonText": "Excel"
    }];
}

var oTable = table.dataTable({					
    "aaSorting": [[0, 'asc']],
    "aLengthMenu": [ [10, 15, 25, 50, 100, 250, -1],	[10, 15, 25, 50, 100, 250, "All"] ],						
    "iDisplayLength": 10,   // set the initial value  
    "sPaginationType" : "bootstrap_full_number",
    "dom": "<'row' <'col-md-3 col-sm-12'l><'col-md-4 col-sm-12'Tf><'col-md-5 col-sm-12'p>><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",                    
    // horizobtal scrollable datatable
    "oTableTools": 
    {
            "sSwfPath": "../assets/global/plugins/datatables/extensions/TableTools/swf/copy_csv_xls_pdf.swf",
            "aButtons": tableToolsButtons
    }
});

var tableWrapper = $('#sample_1_wrapper'); // datatable creates the table wrapper by adding with id {your_table_jd}_wrapper

jQuery('.dataTables_filter input', tableWrapper).addClass("form-control input-small input-inline"); // modify table search input
jQuery('.dataTables_length select', tableWrapper).addClass("form-control input-small"); // modify table per page dropdown
jQuery('.dataTables_length select', tableWrapper).select2(); // initialize select2 dropdown

$('#sample_2_column_toggler input[type="checkbox"]').change(function()
{
    /* Get the DataTables object again - this is not a recreation, just a get of the object */
    var iCol = parseInt($(this).attr("data-column"));
    var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
    oTable.fnSetColumnVis(iCol, (bVis ? false : true));
});