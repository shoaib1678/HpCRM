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
												<th class="text-white">Received Amount</th>
												<th class="text-white">Remaining Amount</th>
												<th class="text-white">Payment Mode</th>
												<th class="text-white">Payment Status</th>
												<th class="text-white">Journal Name</th>
												<th class="text-white">Article Title</th>
												<th class="text-white">Affiliation</th>
												<th class="text-white">Acceptance File</th>
												<th class="text-white">Payment Receipt</th>
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
					<h5 class="modal-title" id="staticBackdropLabel">Remaining Amount</h5>
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
											<label for="journal_name" class="col-form-label">Payment Mode<span style="color: red;">*</span></label>
											<select class="form-control" id="payment_mode" name="payment_mode" onchange="chanoption()">
												<option disabled selected>-- Select Payment Mode</option>
												<option value="Card">Card</option>
											    <option value="UPI">UPI</option>
											    <option value="Net Banking">Net Banking</option>
											    <option value="Bank Transfer">Bank Transfer</option>
											    <option value="Cheque">Cheque</option>
											    <option value="Other">Other</option>
											</select>
										</div>
									</div>
									<div class="col-md-12" id="pmd" style="display: none;">
										<div class="form-group mb-3">
											<label for="p_mode" class="col-form-label">Other<span style="color: red;">*</span></label>
											<input type="text" class="form-control" id="p_mode" name="p_mode" placeholder="Payment Mode">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group mb-3">
											<label for="transaction_id" class="col-form-label">Transaction ID<span style="color: red;">*</span></label>
											<input type="text" class="form-control" id="transaction_id" name="transaction_id" placeholder="Transaction ID">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group mb-3">
											<label for="received_amount" class="col-form-label">Received Amount<span style="color: red;">*</span></label>
											<input type="text" class="form-control" id="received_amount" name="received_amount" placeholder="Received Amount">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group mb-3">
											<label for="receipt" class="col-form-label">Payment Receipt<span style="color: red;">*</span></label>
											<input type="file" class="form-control" id="receipt" name="receipt" placeholder="Payment Receipt">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary btn-sm"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary btn-sm" id="sbmt">Add Payment</button>
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
		function chanoption(){
			var pm = $("#payment_mode").val();
			if(pm == "Bank Transfer"){
				$("#transaction_id").prop("disabled",true);
				$("#pmd").css("display","none");
			}else if(pm == "Other"){
				$("#transsaction_id").prop("disabled",false);
				$("#pmd").css("display","block");
				
			}else{
				$("#transsaction_id").prop("disabled",false);
				$("#pmd").css("display","none");
			}
		}
		$(function() {
			$("form[name='employee_form']")
					.validate(
							{
								rules : {
									payment_mode : {
										required : true,
									},
									received_amount : {
										required : true,
									},
									transaction_id : {
										required : true,
									},
									receipt : {
										required : true,
									},
									p_mode : {
										required : true,
									},
								},
								messages : {
									payment_mode : {
										required : "Please select payment mode.",
									},
									p_mode : {
										required : "Please enter payment mode.",
									},
									received_amount : {
										required : "Please enter received amount.",
									},
									transaction_id : {
										required : "Please enter transaction id.",
									},
									receipt : {
										required : "Please upload payment receipt.",
									},
								},
								submitHandler : function(form) {
									$("#sbmt").html("Please Wait...");
									$("#sbmt").prop("disabled", true);
									var payment_mode = $("#payment_mode").val();
									if(payment_mode == "Other"){
										payment_mode = $("#p_mode").val();
									}
									var received_amount = $("#received_amount").val();
									var transaction_id = $("#transaction_id").val();
									var contact_number = $("#contact_number").val();
									var file = $("#receipt")[0].files[0];
									var sno = $("#sno").val();
									
									var obj = {
										"transaction_id" : transaction_id,
										"payment_mode" : payment_mode,
										"paid_amount" : received_amount,
										"employee_id" : employee_id,
										"module" : "Publication",
										"ad_id" : sno,
									};
									var fd = new FormData();
									 fd.append("file",file);
									 fd.append("paymentdata",JSON.stringify(obj));
										$.ajax({
											url : 'add_payment',
											type : 'post',
											data : fd,
											processData : false,
											contentType :  false,
												success : function(data) {
													if (data['status'] == 'Success') {
														$("#sbmt").html("Save");
														$("#sbmt").prop("disabled", false);
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
														$("#sbmt").html("Save");
														$("#sbmt").prop("disabled", false);
														$('#employee_modal').modal(
																'toggle');
														Swal
																.fire({
																	icon : 'warning',
																	title : 'Already!',
																	text : data['message']
																})
													} else {
														$("#sbmt").html("Save");
														$("#sbmt").prop("disabled", false);
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
						"status" : "Proved",
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
						var string = "<a href='javascript:void(0)' class='fa fa-file' onclick='payment(" + sno + ")' style='font-size: 27px;color: forestgreen;'></a>";
                    	return string;
					}
				},
				{
					"data" : function(data, type,
							dataToSet) {
						var sno = data.sno;
						var contact_id = data.contact_id;
						var string = "<button class='btn btn-success btn-sm' type='button' onclick='payment(" + sno + ")' style='margin-bottom: 3px;'>Add Remaining Payment</button>";
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