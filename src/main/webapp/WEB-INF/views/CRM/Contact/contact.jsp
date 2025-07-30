
<!DOCTYPE html>
<html lang="zxx">

<head>
<jsp:include page="../css.jsp"></jsp:include>
<style type="text/css">
#module-error{
    position: absolute;
    left: 10%;
    top: 93%;
}

.sttus{
    border: 1px solid lightgray;
    padding: 8px;
    border-radius: 3px;
    cursor: pointer;
}
.sttus.active {
  background-color: #007bff;  /* Bootstrap primary blue */
  color: white;
}
</style>
</head>

<body>
	<!--! ================================================================ !-->
	<!--! [Start] Navigation Manu !-->
	<!--! ================================================================ !-->
	<jsp:include page="../header.jsp"></jsp:include>
	<!--! ================================================================ !-->
	<!--! [End]  Navigation Manu !-->
	<!--! ================================================================ !-->
	<!--! ================================================================ !-->
	<!--! [Start] Sidebar !-->
	<!--! ================================================================ !-->
	<jsp:include page="../sidebar.jsp"></jsp:include>
	<!--! ================================================================ !-->
	<!--! [End] Sidebar !-->
	<!--! ================================================================ !-->
	<!--! ================================================================ !-->
	<!--! [Start] Main Content !-->
	<!--! ================================================================ !-->
	<main class="nxl-container">
		<div class="nxl-content">
			<div class="main-content">
				<div class="row">
					<div class="col-lg-12">
						<div style="display: flex;">
							<button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#employee_modal">Add New Contact Number</button>
							<button class="btn btn-primary btn-sm mx-3" data-bs-toggle="modal" data-bs-target="#contact_modal">Upload Sheet</button>
							
						</div>
						
						<div class="card stretch">
							<div class="card-body">
								<div style="text-align: center;">
									<a class="sttus active" href="javasvript:void(0)">Pending</a>
									<a class="sttus" href="javasvript:void(0)">Not Connected</a>
									<a class="sttus" href="javasvript:void(0)">Connected</a>
									<a class="sttus" href="javasvript:void(0)">Inactive</a>
									<a class="sttus" href="javasvript:void(0)">Converted</a>
								</div>
									
								<div class="table-responsive">
									<table class="table table-hover table-striped nowrap" id="employee_table" style="width: 100%;">
										<thead class="bg-primary">
											<tr>
												<th class="text-white">Sno</th>
												<th class="text-white">Contact Number</th>
												<th class="text-white">Name</th>
												<th class="text-white">Email</th>
												<th class="text-white">Status</th>
												<th class="text-white">Actions</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!-- Modal -->
	<div class="modal fade" id="employee_modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">New Contact Number</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form id="employee_form" name="employee_form">
					<div class="modal-body">
						<div class="row px-4 justify-content-between">
							<div class="col-xl-12 mb-3 mb-sm-0">
								<div class="form-group mb-3">
									<label for="mobile_number" class="col-form-label">Contact Number<span style="color: red;">*</span></label>
										<input type="text" class="form-control" id="contact_number" name="contact_number"
											placeholder="Contact Number">
								</div>
								<div class="form-group mb-3">
									<label for="client_name" class="col-form-label">Client Name</label>
										<input type="text" class="form-control" id="client_name" name="client_name"
											placeholder="Client Name">
								</div>
								<div class="form-group mb-3">
									<label for="email" class="col-form-label">Email</label>
										<input type="text" class="form-control" id="email" name="email" placeholder="Email">
								</div>
								
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary btn-sm"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary btn-sm">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="contact_modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">Upload Contact Sheet</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form id="contact_form" name="contact_form">
					<div class="modal-body">
						<div class="row px-4 justify-content-between">
							<div class="col-xl-12 mb-3 mb-sm-0">
								<div class="form-group mb-3">
									<label for="mobile_number" class="col-form-label">Upload Contact Sheet<span style="color: red;">*</span></label>
										<input type="file" class="form-control" id="sheet" name="sheet"
											>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary btn-sm"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary btn-sm">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="status_modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">Change Contact Status</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form id="status_form" name="status_form">
					<div class="modal-body">
						<div class="row px-4 justify-content-between">
							<div class="col-xl-12 mb-3 mb-sm-0">
								<div class="form-group mb-3">
									<label for="mobile_number" class="col-form-label">Status<span style="color: red;">*</span></label>
										<select class="form-control" id="status" name="status" onchange="showinput()">
											<option selected disabled>--Select Status--</option>
											<option value="Not Connected">Not Connected</option>
											<option value="Connected">Connected</option>
											<option value="Converted">Converted</option>
										</select>
								</div>
								<div class="form-group mb-3" id="rem" style="display: none;">
									<label for="client_name" class="col-form-label">Remarks<span style="color: red;">*</span></label>
										<textarea  class="form-control" id="remarks" name="remarks"></textarea>
								</div>
								<div id="mod" style="display: none;">
								<h5>Module<span style="color: red;">*</span></h5>
									<div class="form-check form-switch">
									  <input id="publication" name="module" class="form-check-input" type="checkbox" role="switch" value="Publication"/>
									  <label for="publication" class="form-check-label">Publication</label>
									</div>
									<div class="form-check form-switch">
									  <input id="writing_paper" name="module" class="form-check-input" type="checkbox" role="switch" value="Writing Paper"/>
									  <label for="writing_paper" class="form-check-label">Writing Paper</label>
									</div>
									<div class="form-check form-switch">
									  <input id="authorship" name="module" class="form-check-input" type="checkbox" role="switch" value="Authorship"/>
									  <label for="authorship" class="form-check-label">Authorship</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary btn-sm"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary btn-sm">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="remarks_modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">Add Remarks</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form id="remarks_form" name="remarks_form">
					<div class="modal-body">
						<div class="row px-4 justify-content-between">
							<div class="col-xl-12 mb-3 mb-sm-0">
								<div class="form-group mb-3">
									<label for="rremarks" class="col-form-label">Remarks<span style="color: red;">*</span></label>
										<textarea  class="form-control" id="rremarks" name="rremarks"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary btn-sm"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary btn-sm">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="remarksview_modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">All Remarks</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
					<div class="modal-body">
						<div class="table-responsive">
						<table class="table table-hover table-striped nowrap" id="remarks_table" style="width: 100%;">
										<thead class="bg-primary">
											<tr>
												<th class="text-white">Sno</th>
												<th class="text-white">Employee Name</th>
												<th class="text-white">Remarks</th>
												<th class="text-white">Connected Date</th>
												<th class="text-white">Connected Time</th>
											</tr>
										</thead>
									</table>
								</div>			
					</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="inactive_modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">All Remarks</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
					<div class="modal-body">
						<div class="table-responsive">
						<table class="table table-hover table-striped nowrap" id="inactive_table" style="width: 100%;">
										<thead class="bg-primary">
											<tr>
												<th class="text-white">Sno</th>
												<th class="text-white">Connected Date</th>
												<th class="text-white">Connected Time</th>
											</tr>
										</thead>
									</table>
								</div>			
					</div>
			</div>
		</div>
	</div>
	<!--! ================================================================ !-->
	<!--! [End] Theme Customizer !-->
	<!--! ================================================================ !-->
	<!--! ================================================================ !-->
	<!--! Footer Script !-->
	<!--! ================================================================ !-->
	<!--! BEGIN: Vendors JS !-->
	<input type="hidden" id="sno" name="sno">
	<jsp:include page="../js.jsp"></jsp:include>
	<script type="text/javascript">
		//let employee_id = $("#employee_id").val();
		let employee_id = $("#employee_id").val();
function showinput(){
	var status=$("#status").val();
	if(status == "Connected"){
		$("#rem").css("display","block");
		$("#mod").css("display","none");
	}else if(status == "Converted"){
		$("#rem").css("display","block");
		$("#mod").css("display","block");
	}else{
		$("#rem").css("display","none");
		$("#mod").css("display","none");
	}
}
		$(function() {
			$("form[name='employee_form']")
					.validate(
							{
								rules : {
									contact_number : {
										required : true,
									},
								},
								messages : {
									contact_number : {
										required : "Please enter contact number.",
									},
								},
								submitHandler : function(form) {
									var contact_number = $("#contact_number").val();
									var client_name = $("#client_name").val();
									var email = $("#email").val();
									var sno = $("#sno").val();
									var obj = {
										"contact_number" : contact_number,
										"client_name" : client_name,
										"email" : email,
										"employee_id" : employee_id,
										"sno" : sno,
									};
									$
											.ajax({
												url : 'add_contact',
												type : 'post',
												data : JSON.stringify(obj),
												dataType : 'json',
												contentType : 'application/json',
												success : function(data) {
													if (data['status'] == 'Success') {
														Swal.fire({
																	icon : 'success',
																	title : 'successfully!',
																	text : data['message']
																})
														$('#employee_modal').modal(
																'toggle');
														$('#employee_table')
																.DataTable().ajax
																.reload(null,
																		false);
													} else if (data['status'] == 'Already_Exist') {
														$('#employee_modal').modal(
																'toggle');
														Swal
																.fire({
																	icon : 'warning',
																	title : 'Already!',
																	text : data['message']
																})
													} else {
														$('#employee_modal').modal(
																'toggle');
														Swal
																.fire({
																	icon : 'Sorry',
																	title : 'Invalid!',
																	text : data['message']
																})
													}
												}
											});
								}
							});
		});
		function changestatus(sno){
			$("#sno").val(sno);
			$('#status_modal').modal('toggle');
		}
		function addremarks(sno){
			$("#sno").val(sno);
			$('#remarks_modal').modal('toggle');
		}
		
		$(function() {
			$("form[name='status_form']")
					.validate(
							{
								rules : {
									status : {
										required : true,
									},
									remarks : {
										required : true,
									},
									module : {
										required : true,
									},
								},
								messages : {
									status : {
										required : "Please select status.",
									},
									remarks : {
										required : "Please write remarks.",
									},
									module : {
										required : "Please select module",
									},
								},
								submitHandler : function(form) {
									var status = $("#status").val();
									var remarks = $("#remarks").val();
									var checkedValues = $("input[name='module']:checked").map(function() {
									    return $(this).val();
									}).get().join(",");
									var sno = $("#sno").val();
									var fd = new FormData();
									fd.append("sno", sno);
									fd.append("status", status);
									fd.append("remarks", remarks);
									fd.append("module", checkedValues);
									fd.append("employee_id", 0);
									$.ajax({
										url : 'update_status',
										type : 'post',
										data : fd,
										contentType : false,
										processData : false,
												success : function(data) {
													if (data['status'] == 'Success') {
														$('#status_form')[0].reset();
														Swal.fire({
																	icon : 'success',
																	title : 'successfully!',
																	text : data['message']
																})
														$('#status_modal').modal(
																'toggle');
														$('#employee_table')
																.DataTable().ajax
																.reload(null,
																		false);
													} else if (data['status'] == 'Already_Exist') {
														$('#status_modal').modal(
																'toggle');
														Swal
																.fire({
																	icon : 'warning',
																	title : 'Already!',
																	text : data['message']
																})
													} else {
														$('#status_modal').modal(
																'toggle');
														Swal
																.fire({
																	icon : 'Sorry',
																	title : 'Invalid!',
																	text : data['message']
																})
													}
												}
											});
								}
							});
		});
		
		function data(status) {
		    $("#employee_table").DataTable().clear().destroy(); // Destroy previous instance

		    $("#employee_table").DataTable({
		        dom: "Blfrtip",
		        autoWidth: true,
		        responsive: true,
		        buttons: [
		            {
		                extend: 'pdf',
		                exportOptions: { columns: [0, 1, 2, 3, 4] }
		            },
		            {
		                extend: 'csv',
		                exportOptions: { columns: [0, 1, 2, 3, 4] }
		            },
		            {
		                extend: 'print',
		                exportOptions: { columns: [0, 1, 2, 3, 4] }
		            },
		            {
		                extend: 'excel',
		                exportOptions: { columns: [0, 1, 2, 3, 4] }
		            },
		            {
		                extend: 'pageLength'
		            }
		        ],
		        lengthChange: true,
		        ordering: false,
		        ajax: {
		            url: "get_contact",
		            type: "POST",
		            data: {
		                "employee_id": employee_id,
		                "status": status
		            }
		        },
		        columnDefs: [{
		            "defaultContent": "-",
		            "targets": "_all"
		        }],
		        serverSide: true,
		        columns: [
		            {
		                data: 'SrNo',
		                render: function(data, type, row, meta) {
		                    return meta.row + meta.settings._iDisplayStart + 1;
		                }
		            },
		            { data: "contact_number" },
		            { data: "client_name" },
		            { data: "email" },
		            {
		                data: "status",
		                render: function(data, type, row) {
		                    var badgeClass = '';
		                    var label = data;

		                    if (data.toLowerCase() === 'converted') {
		                        badgeClass = 'badge bg-success';
		                    } else if (data.toLowerCase() === 'connected') {
		                        badgeClass = 'badge bg-warning text-dark';
		                    } else if (data.toLowerCase() === 'pending' || data.toLowerCase() === 'not connected') {
		                        badgeClass = 'badge bg-danger';
		                    } else {
		                        badgeClass = 'badge bg-secondary';
		                    }

		                    return '<span class="' + badgeClass + '">' + label + '</span>';
		                }
		            },
		            {
		                data: function(data, type, dataToSet) {
		                    var sno = data.sno;
		                    var status = data.status;
		                    var string = "<button class='btn btn-primary btn-sm' type='button' onclick='edit(" + sno + ")'>Edit</button>";
		                     string += "<button class='btn btn-primary btn-sm' type='button' onclick='changestatus(" + sno + ")' style='margin-left: 10px;'>Update Status</button>";
		                    if(status === "Connected"){
		                    	string += '<button type="button" class="btn btn-sm btn-primary" onclick="addremarks(' + sno + ')" style="margin-left: 10px;">Add Remarks</button>';
			                    string += '<button type="button" class="btn btn-sm btn-success" onclick="viewremarks(' + sno + ')" style="margin-left: 10px;">View Remarks</button>';
		                    }
		                    if(status === "Inactive"){
			                    string += '<button type="button" class="btn btn-sm btn-success" onclick="viewconnected(' + sno + ')" style="margin-left: 10px;">View</button>';
		                    }
		                    
		                    return string;
		                }
		            }
		        ],
		        lengthMenu: [[5, 10, 25, 50], [5, 10, 25, 50]],
		        select: true
		    });
		}
		data("Pending"); 
		$(document).on('click', '.sttus', function () {
		    var status = $(this).text().trim();

		    // Remove 'active' class from all, then add to clicked
		    $('.sttus').removeClass('active');
		    $(this).addClass('active');

		    // Call data with selected status
		    data(status);
		});
		$(function() {
			$("form[name='remarks_form']")
					.validate(
							{
								rules : {
									rremarks : {
										required : true,
									},
								},
								messages : {
									rremarks : {
										required : "Please write remarks.",
									},
								},
								submitHandler : function(form) {
									var rremarks = $("#rremarks").val();
									var sno = $("#sno").val();
									var obj = {
										"remarks" : rremarks,
										"employee_id" : employee_id,
										"contact_id" : sno,
									};
									$
											.ajax({
												url : 'add_remarks',
												type : 'post',
												data : JSON.stringify(obj),
												dataType : 'json',
												contentType : 'application/json',
												success : function(data) {
													if (data['status'] == 'Success') {
														Swal.fire({
																	icon : 'success',
																	title : 'successfully!',
																	text : data['message']
																})
														$('#remarks_modal').modal(
																'toggle');
																	
													} else if (data['status'] == 'Already_Exist') {
														$('#remarks_modal').modal(
																'toggle');
														Swal
																.fire({
																	icon : 'warning',
																	title : 'Already!',
																	text : data['message']
																})
													} else {
														$('#remarks_modal').modal(
																'toggle');
														Swal
																.fire({
																	icon : 'Sorry',
																	title : 'Invalid!',
																	text : data['message']
																})
													}
												}
											});
								}
							});
		});
		function viewremarks(contact_id) {
			$('#remarksview_modal').modal('toggle');
		    $("#remarks_table").DataTable().clear().destroy(); // Destroy previous instance

		    $("#remarks_table").DataTable({
		        dom: "Blfrtip",
		        autoWidth: true,
		        responsive: true,
		        buttons: [
		            {
		                extend: 'pdf',
		                exportOptions: { columns: [0, 1, 2, 3, 4] }
		            },
		            {
		                extend: 'csv',
		                exportOptions: { columns: [0, 1, 2, 3, 4] }
		            },
		            {
		                extend: 'print',
		                exportOptions: { columns: [0, 1, 2, 3, 4] }
		            },
		            {
		                extend: 'excel',
		                exportOptions: { columns: [0, 1, 2, 3, 4] }
		            },
		            {
		                extend: 'pageLength'
		            }
		        ],
		        lengthChange: true,
		        ordering: false,
		        ajax: {
		            url: "get_remarks",
		            type: "POST",
		            data: {
		                "employee_id": employee_id,
		                "contact_id": contact_id
		            }
		        },
		        columnDefs: [{
		            "defaultContent": "-",
		            "targets": "_all"
		        }],
		        serverSide: true,
		        columns: [
		            {
		                data: 'SrNo',
		                render: function(data, type, row, meta) {
		                    return meta.row + meta.settings._iDisplayStart + 1;
		                }
		            },
		            { data: "employee_name" },
		            { data: "remarks" },
		            { data: "remarksDate" },
		            { data: "connected_time" },
		        ],
		        lengthMenu: [[5, 10, 25, 50], [5, 10, 25, 50]],
		        select: true
		    });
		}
		function viewconnected(contact_id) {
			$('#inactive_modal').modal('toggle');
		    $("#inactive_table").DataTable().clear().destroy(); // Destroy previous instance

		    $("#inactive_table").DataTable({
		        dom: "Blfrtip",
		        autoWidth: true,
		        responsive: true,
		        buttons: [
		            {
		                extend: 'pdf',
		                exportOptions: { columns: [0, 1, 2, 3, 4] }
		            },
		            {
		                extend: 'csv',
		                exportOptions: { columns: [0, 1, 2, 3, 4] }
		            },
		            {
		                extend: 'print',
		                exportOptions: { columns: [0, 1, 2, 3, 4] }
		            },
		            {
		                extend: 'excel',
		                exportOptions: { columns: [0, 1, 2, 3, 4] }
		            },
		            {
		                extend: 'pageLength'
		            }
		        ],
		        lengthChange: true,
		        ordering: false,
		        ajax: {
		            url: "get_inactive",
		            type: "POST",
		            data: {
		                "contact_id": contact_id,
		            }
		        },
		        columnDefs: [{
		            "defaultContent": "-",
		            "targets": "_all"
		        }],
		        serverSide: true,
		        columns: [
		            {
		                data: 'SrNo',
		                render: function(data, type, row, meta) {
		                    return meta.row + meta.settings._iDisplayStart + 1;
		                }
		            },
		            { data: "date" },
		            { data: "time" },
		        ],
		        lengthMenu: [[5, 10, 25, 50], [5, 10, 25, 50]],
		        select: true
		    });
		}
		
		function edit(sno){
			$("#sno").val(sno);
			var fd = new FormData();
			fd.append("sno", sno);
			$.ajax({
				url : 'edit_contact',
				type : 'post',
				data : fd,
				contentType : false,
				processData : false,
						success : function(data) {
							if (data['status'] == 'Success') {
								$('#employee_modal').modal('toggle');
								$("#contact_number").val(data['data'][0].contact_number);
								$("#client_name").val(data['data'][0].client_name);
								$("#email").val(data['data'][0].email);
							}else {
								$('#employee_modal').modal(
										'toggle');
								Swal
										.fire({
											icon : 'Sorry',
											title : 'Invalid!',
											text : data['message']
										})
							}
						}
					});
		}
	</script>
</body>

</html>