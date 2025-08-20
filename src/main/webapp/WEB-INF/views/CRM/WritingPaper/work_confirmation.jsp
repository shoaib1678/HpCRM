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
								<!-- <div style="text-align: center;">
									<a class="sttus active" href="javasvript:void(0)">Pending</a>
									<a class="sttus" href="javasvript:void(0)">Confirmed</a>
								</div> -->
								<div class="table-responsive">
									<table id="employee_table" class="table table-hover table-striped nowrap display responsive" style="width:100%">
										<thead class="bg-primary">
											<tr>
												<th class="text-white">Article Id</th>
												<th class="text-white">Contact Number</th>
												<th class="text-white">email</th>
												<th class="text-white">Total Amount</th>
												<th class="text-white">Received Amount</th>
												<th class="text-white">Remaining Amount</th>
												<th class="text-white">Payment Mode</th>
												<th class="text-white">Payment Status</th>
												<th class="text-white">Writing Type</th>
												<th class="text-white">Title</th>
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
					<h5 class="modal-title" id="staticBackdropLabel">Upload Writing File</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form id="employee_form" name="employee_form">
					<div class="modal-body">
						<div class="row px-4 justify-content-between">
							<div class="col-xl-12 mb-3 mb-sm-0">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group mb-3">
											<label for="file" class="col-form-label">Writing File</label>
											<input type="file" class="form-control" id="file" name="file" >
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
	<input type="hidden" id="contact_id" name="contact_id">
	<jsp:include page="../js.jsp"></jsp:include>
	<script type="text/javascript">
		//let employee_id = $("#employee_id").val();
		let employee_id = $("#employee_id").val();
		$(function() {
			$("form[name='employee_form']")
					.validate(
							{
								rules : {
									file : {
										required : true,
									},
								},
								messages : {
									file : {
										required : "Please upload writing file.",
									},
								},
								submitHandler : function(form) {
									var file = $("#file")[0].files[0];
									var sno = $("#sno").val();
									
									var fd = new FormData();
									 fd.append("file",file);
									 fd.append("sno",sno);
									 fd.append("status","Completed");
										$.ajax({
											url : 'add_writing_file',
											type : 'post',
											data : fd,
											processData : false,
											contentType :  false,
												success : function(data) {
													if (data['status'] == 'Success') {
														$('#employee_table').DataTable().ajax.reload(null, false);
														Swal.fire({
															icon: 'success',
															title: 'Success!',
															text: "Final Work Confirmation Successfully"
														}).then(() => {
															setTimeout(function () {
																window.location.href = 'writing_paper_final_work_details';
															}, 2000);
														});
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
		function payment(sno){
			$("#sno").val(sno);
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
				 responsive: {
				        details: {
				            type: 'inline'
				        }
				    },
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
					url : "get_writing_details",
					type : "POST",
					"data" : {
						"employee_id" : employee_id,
						"status" : "Full Work Confirmation",
					}

				},
				columnDefs : [ {
					"defaultContent" : "-",
					"targets" : "_all"
				} ],
				serverSide : true,
				columns : [ 
					{
						"data" : "article_id"
					}, 
				{
					"data" : "contact_number"
				}, 
				{
					"data" : "email"
				}, 
				{
					"data" : "amount"
				}, 
				{
					"data" : "paid_amount"
				}, 
				{
					"data" : "remaining_amount"
				}, 
				{
					"data" : "payment_mode"
				}, 
				{
					"data" : "payment_status"
				}, 
				{
					"data" : "writing_type"
				}, 
				{
					"data" : "title"
				}, 
				
				{
					"data" : function(data, type,
							dataToSet) {
						var sno = data.sno;
						var contact_id = data.contact_id;
						var string = "<button class='btn btn-success btn-sm' type='button' onclick='confirmation(" + sno + ")' style='margin-bottom: 3px;'>Confirmed</button>";
						string += '<button type="button" class="btn btn-sm btn-success" onclick="getReceipt(' + contact_id + ',\'Writing Paper\')" style="margin-bottom: 3px;">Payment Receipt</button>';
						string += '<button type="button" class="btn btn-sm btn-primary" onclick="addremarks(' + contact_id + ')" style="margin-bottom: 3px;">Add Remarks</button>';
	                    string += '<button type="button" class="btn btn-sm btn-success" onclick="viewremarks(' + contact_id + ')" >View Remarks</button>';
                    
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
		
		function confirmation(sno) {
			$('#employee_modal').modal('toggle');
			$("#sno").val(sno);
		}



	</script>
</body>

</html>