
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
						<button class="btn btn-primary btn-sm" data-bs-toggle="modal"
							data-bs-target="#employee_modal">Register New Employee</button>
						<div class="card stretch">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-hover table-striped nowrap" id="employee_table">
										<thead class="bg-primary">
											<tr>
												<th class="text-white">Sno</th>
												<th class="text-white">Name</th>
												<th class="text-white">Email</th>
												<th class="text-white">Phone Number</th>
												<th class="text-white">Password</th>
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
					<h5 class="modal-title" id="staticBackdropLabel">Employee
						Registration</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form id="employee_form" name="employee_form">
					<div class="modal-body">
						<div class="row px-4 justify-content-between">
							<div class="col-xl-12 mb-3 mb-sm-0">
								<div class="form-group mb-3">
									<label for="employee_name" class="col-form-label">Employee Name<span style="color: red;">*</span></label>
										<input type="text" class="form-control" id="employee_name" name="employee_name"
											placeholder="Employee Name">
								</div>
								<div class="form-group mb-3">
									<label for="email" class="col-form-label">Email<span style="color: red;">*</span></label>
										<input type="text" class="form-control" id="email" name="email"
											placeholder="Email Address">
								</div>
								<div class="form-group mb-3">
									<label for="mobile_number" class="col-form-label">Mobile number<span style="color: red;">*</span></label>
										<input type="text" class="form-control" id="mobile_number" name="mobile_number"
											placeholder="Mobile Number">
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
									employee_name : {
										required : true,
									},
									email : {
										required : true,
									},
									mobile_number : {
										required : true,
									}
								},
								messages : {
									employee_name : {
										required : "Please enter employee name.",
									},
									email : {
										required : "Please enter email.",
									},
									mobile_number : {
										required : "Please enter mobile number.",
									},
								},
								submitHandler : function(form) {
									var employee_name = $("#employee_name").val();
									var email = $("#email").val();
									var mobile_number = $("#mobile_number").val();
									var obj = {
										"employee_name" : employee_name,
										"mobile_number" : mobile_number,
										"email" : email,
										"sno" : 0,
									};
									$
											.ajax({
												url : 'add_employee',
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
					url : "get_employee",
					type : "POST",
					"data" : {
						"employee_id" : employee_id
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
					"data" : "employee_name"
				}, 
				{
					"data" : "email"
				}, 
				{
					"data" : "mobile_number"
				}, 
				{
					"data" : "password"
				}, 
				{
					"data" : "status"
				},
				{
					"data" : function(data, type,
							dataToSet) {
						var sno = data.sno;
						var string = "<button class='btn btn-sm btn-secondary add-new btn-primary btn-xs fa fa-pencil-square-o'  type='button'  onclick='edit("
								+ sno + ")'></button> ";
						//	string +='<button type="button" class="btn btn-sm btn-danger btn-sm ml-1 "  onclick="deletedata('+sno+')" style="margin-left: 10px;">Delete</button>';
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