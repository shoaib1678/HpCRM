<!DOCTYPE html>
<%@page import="java.util.List"%>
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
<%
List<String> journal_name= (List<String>)request.getAttribute("journal_name");
%>
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
						<div class="card stretch">
							<div class="card-body">
								<div style="text-align: center;">
									<a class="sttus active" href="javasvript:void(0)">Converted</a>
									<a class="sttus" href="javasvript:void(0)">Hold</a>
								</div>
								<div class="table-responsive">
									<table class="table table-hover table-striped nowrap" id="employee_table" style="width: 100%;">
										<thead class="bg-primary">
											<tr>
												<th class="text-white">Sno</th>
												<th class="text-white">Contact Number</th>
												<th class="text-white">Name</th>
												<th class="text-white">Email</th>
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
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">Article Details</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form id="employee_form" name="employee_form">
					<div class="modal-body">
						<div class="row px-4 justify-content-between">
							<div class="col-xl-12 mb-3 mb-sm-0">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group mb-3">
											<label for="contact_number" class="col-form-label">Contact Number<span style="color: red;">*</span></label>
											<input type="text" class="form-control" id="contact_number" name="contact_number" placeholder="Contact Number" disabled>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group mb-3">
											<label for="email" class="col-form-label">Email</label>
											<input type="text" class="form-control" id="email" name="email" placeholder="Email">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group mb-3">
											<label for="name" class="col-form-label">Author Name</label>
											<input type="text" class="form-control" id="name" name="name" placeholder="Author NAme">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group mb-3">
											<label for="journal_name" class="col-form-label">Journal Name<span style="color: red;">*</span></label>
											<select class="form-control" id="journal_name" name="journal_name" onchange="getjournal()">
												<option selected disabled>--Select Journal Name--</option>
												<%if(journal_name != null){
													for(String s: journal_name){%>
													<option value="<%=s%>"><%=s%></option>
												<%}} %>
											</select>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group mb-3">
											<label for="title" class="col-form-label">Title<span style="color: red;">*</span></label>
											<select class="form-control" id="title" name="title" onchange="getposition()">
												<option selected disabled>--Select Title--</option>
											</select>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group mb-3">
											<label for="position" class="col-form-label">Position<span style="color: red;">*</span></label>
											<select class="form-control" id="position" name="position">
												<option selected disabled>--Select Position--</option>
											</select>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group mb-3">
											<label for="status" class="col-form-label">Status<span style="color: red;">*</span></label>
											<select class="form-control" id="status" name="status">
												<option selected disabled>--Select Status--</option>
												<option value="Hold">Hold</option>
												<option value="Booked">Booked</option>
											</select>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group mb-3">
											<label for="remarks" class="col-form-label">Remarks<span style="color: red;">*</span></label>
											<textarea class="form-control" id="remarks" name="remarks"></textarea>
										</div>
									</div>
										<div class="col-md-6 bkd" style="display: none;">
											<div class="form-group mb-3">
												<label for="author_id" class="col-form-label">Author ID<span style="color: red;">*</span></label>
												<input type="text" class="form-control" id="author_id" name="author_id" placeholder="Author ID">
											</div>
										</div>
										<div class="col-md-6 bkd" style="display: none;">
											<div class="form-group mb-3">
												<label for="author_affiliation" class="col-form-label">Author Affiliation<span style="color: red;">*</span></label>
												<input type="text" class="form-control" id="author_affiliation" name="author_affiliation" placeholder="Author Affiliation">
											</div>
										</div>
										<div class="col-md-6 bkd" style="display: none;">
											<div class="form-group mb-3">
												<label for="booking_amount" class="col-form-label">Booking Amount<span style="color: red;">*</span></label>
												<input type="text" class="form-control" id="booking_amount" name="booking_amount" placeholder="Booking Amount">
											</div>
										</div>
										<div class="col-md-6 bkd" style="display: none;">
											<div class="form-group mb-3">
												<label for="booking_date" class="col-form-label">Booking Date<span style="color: red;">*</span></label>
												<input type="date" class="form-control" id="booking_date" name="booking_date" placeholder="Booking Date">
											</div>
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
		$("#status").change(function(){
			var val = $(this).val();
			if(val == "Booked"){
				$(".bkd").css("display","block");
			}else{
				$(".bkd").css("display","none");
			}
		})
		$(function() {
			$("form[name='employee_form']")
					.validate(
							{
								rules : {
									journal_name : {
										required : true,
									},
									title : {
										required : true,
									},
									position : {
										required : true,
									},
									status : {
										required : true,
									},
									remarks : {
										required : true,
									},
									author_id : {
										required : true,
									},
									author_affiliation : {
										required : true,
									},
									booking_amount : {
										required : true,
									},
									booking_date : {
										required : true,
									},
								},
								messages : {
									journal_name : {
										required : "Please select writing type.",
									},
									title : {
										required : "Please enter other writing type.",
									},
									position : {
										required : "Please enter title.",
									},
									status : {
										required : "Please enter article id.",
									},
									remarks : {
										required : "Please enter total amount.",
									},
									author_id : {
										required : "Please enter total amount.",
									},
									author_affiliation : {
										required : "Please enter total amount.",
									},
									booking_amount : {
										required : "Please enter total amount.",
									},
									booking_date : {
										required : "Please enter total amount.",
									},
								},
								submitHandler : function(form) {
									var journal_name = $("#journal_name").val();
									var title = $("#title").val();
									var position = $("#position").val();
									var status = $("#status").val();
									var author_id = $("#author_id").val();
									var remarks = $("#remarks").val();
									var author_affiliation = $("#author_affiliation").val();
									var booking_amount = $("#booking_amount").val();
									var booking_date = $("#booking_date").val();
									var name = $("#name").val();
									var email = $("#email").val();
									var total_amount = $("#total_amount").val();
									var sno = $("#sno").val();
									
									var obj = {
										"aa_id" : title,
										"position_id" : position,
										"status" : status,
										"name" : name,
										"email" : email,
										"author_id" : author_id,
										"affiliation" : author_affiliation,
										"booking_amount" : booking_amount,
										"booking_date" : booking_date,
										"remarks" : remarks,
										"employee_id" : employee_id,
										"contact_id" : sno,
									};
									$.ajax({
										url : 'add_author',
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
			getcontactdata(sno);
			$('#employee_modal').modal('toggle');
		}
		function addremarks(sno){
			$("#sno").val(sno);
			$('#remarks_modal').modal('toggle');
		}
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
		            url: "get_converted_contact",
		            type: "POST",
		            data: {
		                "employee_id": employee_id,
		                "status": status,
		                "module" : "Authorship",
		            }
		        },
		        columnDefs: [{
		            "defaultContent": "-",
		            "targets": "_all"
		        }],
		        serverSide: true,
		        columns : [ 
					{
						data : 'SrNo',
						render : function(data, type, row,
								meta) {
							return meta.row
									+ meta.settings._iDisplayStart
									+ 1;
						}
					},
				{
					"data" : "contact_number"
				}, 
				{
					"data" : "client_name"
				}, 
				{
					"data" : "email"
				}, 
				{
					"data" : function(data, type,
							dataToSet) {
						var sno = data.sno;
						var string = "";
						if(status == "Hold"){
							string += "<button class='btn btn-primary btn-sm' type='button' onclick='changestatus(" + sno + ")'>Update Authorship Status</button>";
						}else{
							string += "<button class='btn btn-primary btn-sm' type='button' onclick='changestatus(" + sno + ")'>Update Authorship Position</button>";
						}
                    	string += '<button type="button" class="btn btn-sm btn-primary" onclick="addremarks(' + sno + ')" style="margin-left: 10px;">Add Remarks</button>';
	                    string += '<button type="button" class="btn btn-sm btn-success" onclick="viewremarks(' + sno + ')" style="margin-left: 10px;">View Remarks</button>';
                    
                    return string;
					}
				},
				],
		        lengthMenu: [[5, 10, 25, 50], [5, 10, 25, 50]],
		        select: true
		    });
		}
		data("Converted"); 
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
										"module" : "Authorship",
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
		                "contact_id": contact_id,
		                "module" : "Authorship",
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
		function getjournal(){
			var journal_name = $("#journal_name").val();
			var fd = new FormData();
			fd.append("journal_name", journal_name);
			$.ajax({
				url : 'get_title',
				type : 'post',
				data : fd,
				contentType : false,
				processData : false,
				success : function(data) {
					if (data['status'] == 'Success') {
						$("#title").empty();
					 	$("#title").append("<option disabled selected>--Select Title--</option>");
					 	for(var i = 0; i < data['data'].length; i++){
					 		$("#title").append("<option value='" + data['data'][i].sno +"'>"+ data['data'][i].title +"</option>");
					 	}
					} else {
						swal("Invalid!", data['message'] , "error")
					}
				}
			});

		}
		function getposition(){
			var title = $("#title").val();
			var fd = new FormData();
			fd.append("sno", title);
			$.ajax({
				url : 'get_position',
				type : 'post',
				data : fd,
				contentType : false,
				processData : false,
				success : function(data) {
					if (data['status'] == 'Success') {
						$("#position").empty();
					 	$("#position").append("<option disabled selected>--Select Position--</option>");
					 	
					 	for(var i = 0; i < data['dd'].length; i++){
					 		if(data['dd'][i].status == "Booked"){
					 			$("#position").append("<option value='" + data['dd'][i].sno +"' disabled>"+ data['dd'][i].position +"</option>");
					 		}else{
					 			$("#position").append("<option value='" + data['dd'][i].sno +"'>"+ data['dd'][i].position +"</option>");
					 		}
					 		
					 	}
					} else {
						swal("Invalid!", data['message'] , "error")
					}
				}
			});

		}
		function getcontactdata(sno){
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
								$("#contact_number").val(data['data'][0].contact_number);
								$("#name").val(data['data'][0].client_name);
								$("#email").val(data['data'][0].email);
								
							}else {
								Swal.fire({
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