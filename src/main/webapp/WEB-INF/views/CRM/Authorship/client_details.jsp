<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
.price-tag {
      font-weight: bold;
      color: #198754;
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
												<th class="text-white">Author ID</th>
												<th class="text-white">Author Name</th>
												<th class="text-white">Email</th>
												<th class="text-white">Contact Number</th>
												<th class="text-white">affiliation</th>
												<th class="text-white">Journal Name</th>
												<th class="text-white">Title</th>
												<th class="text-white">Booked Position</th>
												<th class="text-white">Booking Amount</th>
												<th class="text-white">Booking Date</th>
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
									<div class="col-md-12">
										<div class="form-group mb-3">
											<label for="title" class="col-form-label">Title<span style="color: red;">*</span></label>
											<input type="text" class="form-control" id="title" name="title" placeholder="Title">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group mb-3">
											<label for="journal_name" class="col-form-label">Journal Name</label>
											<input type="text" class="form-control" id="journal_name" name="journal_name" placeholder="Journal Name">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group mb-3">
											<label for="total_position" class="col-form-label">Total Position</label>
											<input type="text" class="form-control" id="total_position" name="total_position" onkeyup="addrow()" placeholder="Total Position">
										</div>
									</div>
									
									<div class="col-md-12">
										 <table id="requisitionData" class="table table-striped nowrap " style="width:100%">
							                 <thead class="bg-primary">
							                    <tr>
							                     	<th class="text-white">Position</th>
							                     	<th class="text-white">Position Amount</th>
							                     </tr>
							                 </thead>
							                 <tbody id="tbody">
												<tr>
													<td><input type="text" class="form-control p-2" value="Corresponding Author" name="position1" id="position1" disabled></td>
													<td><input type="text" class="form-control p-2" name="position_amount1" id="position_amount1"></td>
												</tr>
											</tbody>
							               </table>
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
	
	<div class="modal fade" id="positionview_modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-body">
		          <h6 class="card-title text-primary fw-bold m-3" id="atitle"></h6>
		          <p class="text-muted mb-3 mx-3" id="ajournal"></p>
		
		          <div class="table-responsive">
		            <table class="table table-bordered table-hover align-middle text-center">
		              <thead class="table-primary">
		                <tr>
		                  <th>Position</th>
		                  <th>Price</th>
		                  <th>Status</th>
		                  <th>Booking Price</th>
		                  <th>Booked By</th>
		                </tr>
		              </thead>
		              <tbody id="tbody1">
		               
		              </tbody>
		            </table>
		      </div>
		      </div>
		      <div class="modal-footer">
						<button type="button" class="btn btn-secondary btn-sm"
							data-bs-dismiss="modal">Close</button>
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
		function received(sno){
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
					$.ajax({
						url : 'received_authorship_payment',
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
									text: data['message']
								}).then(() => {
									setTimeout(function () {
										window.location.href = 'authorship_initial_payment';
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
		$(function() {
			$("form[name='employee_form']")
					.validate(
							{
								rules : {
									title : {
										required : true,
									},
									journal_name : {
										required : true,
									},
									total_position : {
										required : true,
									},
								},
								messages : {
									
									title : {
										required : "Please enter title.",
									},
									journal_name : {
										required : "Please enter journal name.",
									},
									total_position : {
										required : "Please enter total position.",
									},
								},
								submitHandler : function(form) {
									var title = $("#title").val();
									var journal_name = $("#journal_name").val();
									var total_position = $("#total_position").val();
									var c_amount = $("#c_amount").val();
									var sno = 0;
									var totaldata2 = $('#tbody > tr').length; 
									var totalrows2 = parseInt(totaldata2);
									
									var obj = {
										"title" : title,
										"journal_name" : journal_name,
										"total_position" : total_position,
										"available_position" : total_position,
										"corresponding_amount" : c_amount,
										"ap" : []
									};
									for (var i = 1; i <= totalrows2; i++) {
										var str = "#position" + i;
										var str1 = "#position_amount" + i;

										var p = $(str).val();
										var ps = $(str1).val();
										obj.ap.push({
											'position' : p,
											'position_amount' : ps,
										});
									}
									$.ajax({
										url : 'add_aa',
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
					url : "get_author",
					type : "POST",
					data: {
		                "employee_id": employee_id,
		                "status": "Booked"
		            }
				},
				columnDefs : [ {
					"defaultContent" : "-",
					"targets" : "_all"
				} ],
				serverSide : true,
				columns : [ 
					
				{
					"data" : "author_id"
				}, 
				{
					"data" : "name"
				}, 
				{
					"data" : "email"
				}, 
				{
					"data" : "contact_number"
				}, 
				{
					"data" : "affiliation"
				}, 
				{
					"data" : "journal_name"
				}, 
				{
					"data" : "title"
				}, 
				{
					"data" : "position"
				}, 
				{
					"data" : "booking_amount"
				}, 
				{
					"data" : "booking_date"
				}, 
				{
					"data" : function(data, type,
							dataToSet) {
						var sno = data.sno;
						var contact_id = data.contact_id;
						var string = "<button class='btn btn-success btn-sm' type='button' onclick='received(" + sno + ")' style='margin-bottom: 3px;'>Received Initial Payment</button>";
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
		function view(sno) {
			$('#positionview_modal').modal('toggle');

			var fd = new FormData();
			fd.append("sno", sno);

			$.ajax({
				url: 'get_position',
				type: 'post',
				data: fd,
				contentType: false,
				processData: false,
				success: function(data) {
					if (data['status'] === 'Success') {
						$("#atitle").html(data['data'][0].title);
						$("#ajournal").html("<em>Journal Name: " + data['data'][0].journal_name + "</em>");
						var ap = data['data'][0].ap;
						var html = '';
						$('#tbody1').html("");

						for (var i = 0; i < ap.length; i++) {
							let badgeClass = '';
							if (ap[i].status === 'Booked') {
								badgeClass = 'danger';
							} else if (ap[i].status === 'Hold') {
								badgeClass = 'warning';
							} else {
								badgeClass = 'success';
							}

							let ba = "-";
							let bb = "-";
							if (ap[i].booked_amount != null && ap[i].booked_amount !== "") {
								ba = "₹ " + ap[i].booked_amount;
							}
							if (ap[i].employee_name != null && ap[i].employee_name !== "") {
								bb = ap[i].employee_name;
							}

							// Convert numeric position to ordinal
							let positionLabel = ap[i].position;
							if (!isNaN(positionLabel)) {
								let suffix = 'th';
								if (positionLabel % 10 === 1 && positionLabel % 100 !== 11) suffix = 'st';
								else if (positionLabel % 10 === 2 && positionLabel % 100 !== 12) suffix = 'nd';
								else if (positionLabel % 10 === 3 && positionLabel % 100 !== 13) suffix = 'rd';
								positionLabel = positionLabel + suffix;
							}

							// Apply bold row if "Corresponding Author"
							let boldStart = '';
							let boldEnd = '';
							if (ap[i].position === 'Corresponding Author') {
								boldStart = '<strong>';
								boldEnd = '</strong>';
							}

							html += '<tr id="rowss' + (i + 1) + '">';
							html += '<td>' + boldStart + positionLabel + boldEnd + '</td>';
							html += '<td class="price-tag">' + boldStart + '₹' + ap[i].position_amount + boldEnd + '</td>';
							html += '<td>' + boldStart + '<span class="badge bg-' + badgeClass + '">' + ap[i].status + '</span>' + boldEnd + '</td>';
							html += '<td>' + boldStart + ba + boldEnd + '</td>';
							html += '<td>' + boldStart + bb + boldEnd + '</td>';
							html += '</tr>';
						}

						$('#tbody1').append(html);

					} else {
						Swal.fire({
							icon: 'error',
							title: 'Invalid!',
							text: data['message']
						});
					}
				}
			});
		}

	</script>
</body>

</html>