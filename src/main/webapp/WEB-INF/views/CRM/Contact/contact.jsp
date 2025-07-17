
<!DOCTYPE html>
<html lang="zxx">

<head>
<jsp:include page="../css.jsp"></jsp:include>
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
								<div class="table-responsive">
									<table class="table table-hover table-striped nowrap" id="employee_table">
										<thead class="bg-primary">
											<tr>
												<th class="text-white">Sno</th>
												<th class="text-white">Contact Number</th>
												<th class="text-white">Name</th>
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
										<input type="text" class="form-control" id="Client_name" name="client_name"
											placeholder="Client Name">
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
	<!--! ================================================================ !-->
	<!--! [End] Theme Customizer !-->
	<!--! ================================================================ !-->
	<!--! ================================================================ !-->
	<!--! Footer Script !-->
	<!--! ================================================================ !-->
	<!--! BEGIN: Vendors JS !-->
	<jsp:include page="../js.jsp"></jsp:include>
	<script type="text/javascript">
		//let employee_id = $("#employee_id").val();
		let employee_id = 0;

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
									var obj = {
										"contact_number" : contact_number,
										"client_name" : client_name,
										"employee_id" : employee_id,
										"sno" : 0,
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
					url : "get_contact",
					type : "POST",
					"data" : {
						"employee_id" : employee_id,
						"status" : "Pending",
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
					"data" : function(data, type,
							dataToSet) {
						var sno = data.sno;
						var string = "<button class='btn btn-primary btn-sm'  type='button'  onclick='edit("+ sno + ")'>Change Status</button> ";
						string +='<button type="button" class="btn btn-sm btn-success"  onclick="addremarks('+sno+')" style="margin-left: 10px;">Remarks</button>';
						return string;
					}
				},
				],
				"lengthMenu" : [ [ 5, 10, 25, 50 ], [ 5, 10, 25, 50 ] ],
				select : true
			});
		}
		data();
	</script>
</body>

</html>