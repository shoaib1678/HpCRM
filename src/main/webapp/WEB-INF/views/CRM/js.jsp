<!-- ✅ jQuery (must come first!) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="assets/vendors/js/vendors.min.js"></script>
<script src="assets/vendors/js/daterangepicker.min.js"></script>
<script src="assets/vendors/js/apexcharts.min.js"></script>
<script src="assets/vendors/js/circle-progress.min.js"></script>
<script src="assets/js/common-init.min.js"></script>
<script src="assets/js/dashboard-init.min.js"></script>
<script src="assets/js/theme-customizer-init.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.21.0/jquery.validate.min.js"></script>
<!-- ✅ DataTables core -->
<!-- DataTables JS -->
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js"></script>

<!-- ✅ Buttons extension -->
<script src="https://cdn.datatables.net/buttons/2.4.1/js/dataTables.buttons.min.js"></script>

<!-- ✅ JSZip for Excel export -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>

<!-- ✅ pdfmake for PDF export -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>

<!-- ✅ Buttons for HTML5, Print, and Column Visibility -->
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.print.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.colVis.min.js"></script>

<script>
function getNotification() {
	  var employee_id = $("#employee_id").val();
	  var from_date = "";
	  var to_date = "";
	  var fromDate = $("#from_date").val();
	  var toDate = $("#to_date").val();
	  if(fromDate != null && fromDate != "" && to_date != null && to_date != ""){
		  from_date = fromDate;
		  to_date = toDate;
	  }else{
		  var today = new Date();
		    var dd = String(today.getDate()).padStart(2, '0');
		    var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0
		    var yyyy = today.getFullYear();
		    from_date = yyyy + '-' + mm + '-' + dd;
		    var pastDate = new Date();
		    pastDate.setDate(pastDate.getDate() - 30);
		    var dd2 = String(pastDate.getDate()).padStart(2, '0');
		    var mm2 = String(pastDate.getMonth() + 1).padStart(2, '0');
		    var yyyy2 = pastDate.getFullYear();
		     to_date = yyyy2 + '-' + mm2 + '-' + dd2;
	  }

	  if (employee_id != null && employee_id != "") {
	    var fd = new FormData();
	    fd.append("employee_id", employee_id);
	    fd.append("from_date", from_date);
	    fd.append("to_date", to_date);

	    $.ajax({
	      url: 'get_notifications',
	      type: 'post',
	      data: fd,
	      contentType: false,
	      processData: false,
	      success: function(data) {
	        if (data['status'] == 'Success') {
	          $("#not").html(""); // clear container
	          let html = "";
				$("#count").html(data['taday'].length);
	          for (var i = 0; i < data['data'].length; i++) {
	            let notif = data['data'][i]; // assign object

	            html +=
	              "<div class='notifications-item'>" +
	                "<div class='notifications-desc'>" +
	                  "<a href='javascript:void(0);' class='font-body text-truncate-2-line'>" +
	                    ""+ notif.contact_number +" (" + notif.name + ")" +
	                  "</a>" +
	                  "<span style='font-size:12px;'>" + notif.remarks + "</span>" +
	                  "<div class='d-flex justify-content-between align-items-center mt-2'>" +
	                    "<div class='notifications-date text-muted border-bottom border-bottom-dashed'>" + notif.notify_date + "("+notif.employee_name+")</div>" +
	                    "<div class='d-flex align-items-center float-end gap-2'>" +
	                      "<a href='javascript:void(0);' class='d-block wd-8 ht-8 rounded-circle bg-gray-300' " +
	                         "data-bs-toggle='tooltip' title='Make as Read' aria-label='Make as Read'></a>" +
	                      "<a href='javascript:void(0);' class='text-danger' data-bs-toggle='tooltip' title='Remove'>" +
	                        "<i data-feather='x' class='fs-6'></i>" +
	                      "</a>" +
	                    "</div>" +
	                  "</div>" +
	                "</div>" +
	              "</div>";
	          }

	          $("#not").html(html);

	          // Feather icons re-render
	          feather.replace();

	          // Re-init Bootstrap tooltips
	          const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle=\"tooltip\"]');
	          [...tooltipTriggerList].map(el => new bootstrap.Tooltip(el));

	        } else {
	          $('#employee_modal').modal('toggle');
	          Swal.fire({
	            icon: 'error',
	            title: 'Invalid!',
	            text: data['message']
	          });
	        }
	      }
	    });
	  }
	}

getNotification();
</script>

