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
												<th class="text-white">Name</th>
												<th class="text-white">Contact Number</th>
												<th class="text-white">email</th>
												<th class="text-white">Total Amount</th>
												<th class="text-white">Published Date</th>
												<th class="text-white">Publication URL</th>
												<th class="text-white">Publication PDF</th>
												<th class="text-white">Journal Name</th>
												<th class="text-white">Article Title</th>
												<th class="text-white">Affiliation</th>
												<th class="text-white">Acceptance File</th>
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
					<h5 class="modal-title" id="staticBackdropLabel">Publication Details</h5>
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
											<label for="publication_url" class="col-form-label">Publication Url<span style="color: red;">*</span></label>
											<input type="url" class="form-control" id="publication_url" name="publication_url" placeholder="Publication Url">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group mb-3">
											<label for="publication_pdf" class="col-form-label">Publication Pdf</label>
											<input type="file" class="form-control" id="publication_pdf" name="publication_pdf" >
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group mb-3">
											<label for="publication_date" class="col-form-label">Publication Date<span style="color: red;">*</span></label>
											<input type="date" class="form-control" id="publication_date" name="publication_date" placeholder="Received Amount">
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
									publication_url : {
										required : true,
									},
									publication_pdf : {
										required : true,
									},
									publication_date : {
										required : true,
									},
								},
								messages : {
									publication_url : {
										required : "Please enter publication url.",
									},
									publication_pdf : {
										required : "Please upload publication pdf.",
									},
									publication_date : {
										required : "Please enter publication date.",
									},
								},
								submitHandler : function(form) {
									var publication_url = $("#publication_url").val();
									var publication_date = $("#publication_date").val();
									var publication_pdf = $("#publication_pdf")[0].files[0];
									var sno = $("#sno").val();
									
									var obj = {
										"publication_url" : publication_url,
										"publication_date" : publication_date,
										"employee_id" : employee_id,
										"ad_id" : sno,
									};
									var fd = new FormData();
									 fd.append("publicationpdf",publication_pdf);
									 fd.append("publicationdata",JSON.stringify(obj));
										$.ajax({
											url : 'add_publication_details',
											type : 'post',
											data : fd,
											processData : false,
											contentType :  false,
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
					url : "get_articledetails",
					type : "POST",
					"data" : {
						"employee_id" : employee_id,
						"status" : "Published",
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
					"data" : "client_name"
				}, 
				{
					"data" : "contact_number"
				}, 
				{
					"data" : "email"
				}, 
				{
					"data" : "dealed_amount"
				}, 
				{
					  data: 'publication_date',
					  render: function (data, type, row) {
					    if (!data) return '';

					    const date = new Date(data);
					    const options = { timeZone: "Asia/Kolkata" };
					    const istDate = new Date(date.toLocaleString("en-US", options));
					    const day = String(istDate.getDate()).padStart(2, '0');
					    const month = String(istDate.getMonth() + 1).padStart(2, '0');
					    const year = istDate.getFullYear();

					    return day+"-"+month+"-"+year;
					  }
					},
 
				{
					"data" : "url"
				}, 
				{
					"data":function(data,type,dataToSet){
			      		var fileName = data.pdf;
			      		if(fileName != null  && fileName != ""){
			      			return '<a href="displaydocument?url=' + fileName + '" target="_blank" title="Open PDF" style="color:#d9534f; text-decoration:none;">' +
			      	       '<i class="fa fa-file-pdf-o" aria-hidden="true" style="font-size:18px;"></i>Publication Pdf</a>';

			      		}else{
			      			return "NA"
			      		}
			        	
			        }
				},
				{
					"data" : "journal_name"
				}, 
				{
					"data" : "article_title"
				}, 
				{
					"data" : "affilliation"
				}, 
				{
					"data":function(data,type,dataToSet){
			      		var fileName = data.acceptance_file;
			      		if(fileName != null  && fileName != ""){
			      			return '<a href="displaydocument?url=' + fileName + '" target="_blank" title="Open PDF" style="color:#d9534f; text-decoration:none;">' +
			      	       '<i class="fa fa-file-pdf-o" aria-hidden="true" style="font-size:18px;"></i>Acceptance File</a>';

			      		}else{
			      			return "NA"
			      		}
			        	
			        }
				},
				{
					"data" : function(data, type,
							dataToSet) {
						var sno = data.sno;
						var contact_id = data.contact_id;
						var string = "";
						string += '<button type="button" class="btn btn-sm btn-success" onclick="getReceipt(' + contact_id + ',\'Publication\')" style="margin-bottom: 3px;">Payment Receipt</button>';
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
									var sno = $("#sno").val();
									var obj = {
										"remarks" : rremarks,
										"employee_id" : employee_id,
										"module" : "Publication",
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
		                "module" : "Publication",
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
								if(data['data'][0].client_name != null && data['data'][0].client_name != ""){
									$("#client_name").val(data['data'][0].client_name);
								}
								
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
		function edit(sno){
			$("#sno").val(sno);
			$('#employee_modal').modal('toggle');
			var fd = new FormData();
			fd.append("sno", sno);
			$.ajax({
				url : 'edit_articledetails',
				type : 'post',
				data : fd,
				contentType : false,
				processData : false,
						success : function(data) {
							if (data['status'] == 'Success') {
								$("#contact_number").val(data['data'][0].contact_number);
								$("#client_name").val(data['data'][0].client_name);
								$("#journal_name").val(data['data'][0].journal_name);
								$("#email").val(data['data'][0].email);
								$("#affiliation").val(data['data'][0].affilliation);
								$("#article_id").val(data['data'][0].article_id);
								$("#dealed_amount").val(data['data'][0].dealed_amount);
								$("#article_title").val(data['data'][0].article_title);
								$("#contact_id").val(data['data'][0].contact_id);
								
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
		function Confirm(sno) {
			Swal.fire({
				title: 'Are you sure?',
				text: "Do you want to continue?",
				icon: 'question',
				showCancelButton: true,
				confirmButtonText: 'Yes',
				cancelButtonText: 'No',
				reverseButtons: true
			}).then((result) => {
				if (result.value === true) {
					var fd = new FormData();
					fd.append("sno", sno);
					fd.append("status", "Received");
					$.ajax({
						url : 'send_foracceptance',
						type : 'post',
						data : fd,
						contentType : false,
						processData : false,
						success : function(data) {
							if (data['status'] == 'Success') {
								$('#employee_table').DataTable().ajax.reload(null, false);
								Swal.fire({
									icon: 'success',
									title: 'Success!',
									text: "Payment recieved successfully"
								}).then(() => {
									setTimeout(function () {
										window.location.href = 'manage_acceptance';
									}, 2000);
								});
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
			});

		}



	</script>
</body>

</html>