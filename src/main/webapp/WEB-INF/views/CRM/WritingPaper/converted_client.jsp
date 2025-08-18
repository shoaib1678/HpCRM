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
						<div class="card stretch">
							<div class="card-body">
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
											<label for="contact_number" class="col-form-label">Writing Type</label>
											<select class="form-control" id="writing_type" name="writing_type">
												<option selected disabled>--Select--</option>
												<option value="Research Article">Research Article</option>
												<option value="Review Article">Review Article</option>
												<option value="Other">Other</option>
											</select>
										</div>
									</div>
									<div class="col-md-6" id="wtype" style="display: none;">
										<div class="form-group mb-3">
											<label for="w_type" class="col-form-label">Other<span style="color: red;">*</span></label>
											<input type="text" class="form-control" id="w_type" name="w_type" placeholder="Writing Type">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group mb-3">
											<label for="title" class="col-form-label">Title<span style="color: red;">*</span></label>
											<input type="text" class="form-control" id="title" name="title" placeholder="Title">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group mb-3">
											<label for="article_id" class="col-form-label">Article Id<span style="color: red;">*</span></label>
											<input type="text" class="form-control" id="article_id" name="article_id" placeholder="Article Id">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group mb-3">
											<label for="total_amount" class="col-form-label">Total Amount<span style="color: red;">*</span></label>
											<input type="text" class="form-control" id="total_amount" name="total_amount" placeholder="Total Amount">
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
		$("#writing_type").change(function(){
			var val = $(this).val();
			if(val == "Other"){
				$("#wtype").css("display","block");
			}else{
				$("#wtype").css("display","none");
			}
		})
		$(function() {
			$("form[name='employee_form']")
					.validate(
							{
								rules : {
									writing_type : {
										required : true,
									},
									w_type : {
										required : true,
									},
									article_id : {
										required : true,
									},
									title : {
										required : true,
									},
									total_amount : {
										required : true,
									},
								},
								messages : {
									writing_type : {
										required : "Please select writing type.",
									},
									w_type : {
										required : "Please enter other writing type.",
									},
									title : {
										required : "Please enter title.",
									},
									article_id : {
										required : "Please enter article id.",
									},
									total_amount : {
										required : "Please enter total amount.",
									},
								},
								submitHandler : function(form) {
									var writing_type = $("#writing_type").val();
									if(writing_type == "Other"){
										writing_type = $("#w_type").val();
									}else{
										writing_type = $("#writing_type").val();
									}
									var title = $("#title").val();
									var contact_number = $("#contact_number").val();
									var email = $("#email").val();
									var article_id = $("#article_id").val();
									var total_amount = $("#total_amount").val();
									var sno = $("#sno").val();
									
									var obj = {
										"writing_type" : writing_type,
										"title" : title,
										"email" : email,
										"article_id" : article_id,
										"contact_number" : contact_number,
										"amount" : total_amount,
										"employee_id" : employee_id,
										"contact_id" : sno,
									};
									$.ajax({
										url : 'add_writing_details',
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
		
		function data() {
			$("#employee_table").DataTable({
				dom : "Blfrtip",
				autoWidth : true,
				responsive : true,
				buttons : [ {
					extend : 'pdf',
					exportOptions : {
						columns : [ 0, 1, 2, 3, 4 ]
					}
				}, {
					extend : 'csv',
					exportOptions : {
						columns : [ 0, 1, 2, 3, 4 ]
					}

				}, {
					extend : 'print',
					exportOptions : {
						columns : [ 0, 1, 2, 3, 4 ]
					}

				}, {
					extend : 'excel',
					exportOptions : {
						columns : [ 0, 1, 2, 3, 4 ]
					}
				}, {
					extend : 'pageLength'
				} ],
				lengthChange : true,
				ordering : false,
				ajax : {
					url : "get_converted_contact",
					type : "POST",
					"data" : {
						"employee_id" : employee_id,
						"status" : "Converted",
						"module" : "Writing Paper",
					}

				},
				columnDefs : [ {
					"defaultContent" : "-",
					"targets" : "_all"
				} ],
				serverSide : true,
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
	                data: "status",
	                render: function(data, type, row) {
	                    var badgeClass = '';
	                    var label = data;

	                    if (data.toLowerCase() === 'converted') {
	                        badgeClass = 'badge bg-success';
	                    } 

	                    return '<span class="' + badgeClass + '">' + label + '</span>';
	                }
	            },
				{
					"data" : function(data, type,
							dataToSet) {
						var sno = data.sno;
						var string = "<button class='btn btn-primary btn-sm' type='button' onclick='changestatus(" + sno + ")'>Add Article Details</button>";
	                    
                    	string += '<button type="button" class="btn btn-sm btn-primary" onclick="addremarks(' + sno + ')" style="margin-left: 10px;">Add Remarks</button>';
	                    string += '<button type="button" class="btn btn-sm btn-success" onclick="viewremarks(' + sno + ')" style="margin-left: 10px;">View Remarks</button>';
                    
                    return string;
					}
				},
				],
				"lengthMenu" : [ [ 5, 10, 25, 50 ], [ 5, 10, 25, 50 ] ],
				select : true
			});
		}
		data();
		
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
									var rnotify_date = $("#rnotify_date").val();
									var sno = $("#sno").val();
									var obj = {
										"remarks" : rremarks,
										"employee_id" : employee_id,
										"notify_date" : rnotify_date,
										"module" : "Writing Paper",
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
		                "module" : "Writing Paper",
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