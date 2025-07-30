
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

<body>
     <main class="auth-minimal-wrapper" style="background-image: url('assets/images/bglogin.jpg'); background-repeat: no-repeat; background-size: cover;">
        <div class="auth-minimal-inner">
            <div class="minimal-card-wrapper">
                <div class="card mb-4 mt-5 mx-4 mx-sm-0 position-relative fade-in-top">
                    <div class="bg-white p-2 rounded-circle shadow-lg position-absolute translate-middle top-0 start-50" style="width: 120px;">
                        <img src="assets/images/logo.avif" alt="" class="img-fluid" style="border-radius: 40px;">
                    </div>
                    <div class="card-body p-sm-5">
                        <h2 class="fs-20 fw-bolder mb-4">Login</h2>
                        <h4 class="fs-13 fw-bold mb-2">Welcome to Halicon Publication !!</h4>
						<p class="fs-12 fw-medium text-muted">Sign in to access your Halicon CRM dashboard and streamline your publishing workflow.</p>

                        <form action="dashboard" class="w-100 mt-4 pt-2" id="login" name="login" method ="post">
                            <div class="mb-4">
                                <input type="email" class="form-control" placeholder="Email or Username"  id="email" name="email">
                            </div>
                            <div class="mb-3">
                                <input type="password" class="form-control" placeholder="Password" id="password" name="password">
                                 <span class="input-group-text vieweye cursor-pointer" id="password-addon"><i class="fa-solid fa-eye-slash"></i></span>
                            </div>
                            <div class="d-flex align-items-center justify-content-between">
                                <div>
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="rememberMe">
                                        <label class="custom-control-label c-pointer" for="rememberMe">Remember Me</label>
                                    </div>
                                </div>
                                <div>
                                    <a href="auth-reset-minimal.html" class="fs-11 text-primary">Forget password?</a>
                                </div>
                            </div>
                            <div class="mt-5">
                                <button type="submit" class="btn btn-lg btn-primary w-100">Login</button>
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
			$("form[name='login']").validate({
				rules : {
					email : {
						required : true,
					},
					password : {
						required : true,
					}
				},
				messages : {
					email : "Please enter valid username",
					password : "Please enter password",

				},
				submitHandler : function(form) {
					$("#bttnn").html("Please Wait..");
					var email = $("#email").val();
					var password = $("#password").val();
					
					var fd = new FormData();
					
					fd.append("email",email);
					fd.append("password",password);
					

					$.ajax({
						url : 'checklogin',
						type : 'post',
						data : fd,
						contentType : false,
						processData : false,
						success : function(data) {

							if (data['status'] == 'Success') {
								$("#bttnn").html("Success");
								form.submit();
							
							} else{
								$("#bttnn").html("Invalid Login Credentials");
								setTimeout(function() {
									$("#bttnn").html("Login");
							      }, 3000);
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