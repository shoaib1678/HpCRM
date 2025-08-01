
<!DOCTYPE html>
<html lang="zxx">

<head>
    <jsp:include page="../css.jsp"></jsp:include>
    <style type="text/css">
    @keyframes fadeInFromTop {
  0% {
    opacity: 0;
    transform: translateY(-90px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

.fade-in-top {
  animation: fadeInFromTop 0.8s ease-out forwards;
}
.vieweye{
    background: none;
    width: min-content;
    position: absolute;
    top: 57%;
    left: 78%;
    border: none;
    cursor: pointer;
}
    </style>
</head>
<%String sno = (String)request.getAttribute("sno"); %>
<body>
     <main class="auth-minimal-wrapper" style="background-image: url('assets/images/bglogin.jpg'); background-repeat: no-repeat; background-size: cover;">
        <div class="auth-minimal-inner">
            <div class="minimal-card-wrapper">
                <div class="card mb-4 mt-5 mx-4 mx-sm-0 position-relative fade-in-top">
                    <div class="bg-white p-2 rounded-circle shadow-lg position-absolute translate-middle top-0 start-50" style="width: 120px;">
                        <img src="assets/images/logo.avif" alt="" class="img-fluid" style="border-radius: 40px;">
                    </div>
                    <div class="card-body p-sm-5">
                        <h2 class="fs-20 fw-bolder mb-4">Resetting</h2>
                        <h4 class="fs-13 fw-bold mb-2">Reset to your password</h4>
                        <form id="reset_form" name="reset_form" class="w-100 mt-4 pt-2">
                            <div class="mb-4">
                                <input type="password" id="password" name="password" class="form-control" placeholder="New Password">
                            </div>
                            <div class="mb-4">
                                <input type="password" id="cpassword" name="cpassword" class="form-control" placeholder="Confirm Password" required>
                            </div>
                            <input type="hidden" id="sno" name="sno" value="<%=sno%>">
                            <div class="mt-5">
                                <button type="submit" class="btn btn-lg btn-primary w-100" id="bttnn">Save Change</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
   <jsp:include page="../js.jsp"></jsp:include>
   <script>
		$(function() {
			$("form[name='reset_form']").validate({
				rules : {
					password : {
						required : true,
						minlength: 6
					},
					cpassword : {
						required : true,
						 equalTo: "#password"
					}
				},
				messages : {
					 password: {
					    required: "Please enter new password",
					    minlength: "Password must be at least 6 characters long"
					  },
					  confirm_password: {
					    required: "Please enter confirm password",
					    equalTo: "Passwords do not match"
					  }
				},
				submitHandler : function(form) {
					$("#bttnn").html("Please Wait..");
					var sno = $("#sno").val();
					var password = $("#password").val();
					
					var fd = new FormData();
					
					fd.append("sno",sno);
					fd.append("password",password);
					

					$.ajax({
						url : 'reset_password',
						type : 'post',
						data : fd,
						contentType : false,
						processData : false,
						success : function(data) {

							if (data['status'] == 'Success') {
								$("#bttnn").html("Success");
								setTimeout(function() {
									window.location.href = './';
							      }, 2000);					
							} else{
								$("#bttnn").html("Invalid Login Credentials");
								setTimeout(function() {
									$("#bttnn").html("Reset Now");
							      }, 2000);
							}
							
						}
					});
				}
			});
		});
		
		/* Show password input value */

		$("#password-addon").click(function() {
		    if($("#password-addon i").hasClass('fa-eye-slash')){
		          $("#password-addon i").removeClass('fa-eye-slash');
		          $("#password-addon i").addClass('fa-eye');
		          $('#password').attr('type','text');
		        }else{
		          $("#password-addon i").removeClass('fa-eye');
		          $("#password-addon i").addClass('fa-eye-slash');  
		          $('#password').attr('type','password');
		        }
		  });
	</script>
</body>

</html>