
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
    </style>
</head>

<body>
     <main class="auth-minimal-wrapper">
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

                        <form action="index.html" class="w-100 mt-4 pt-2">
                            <div class="mb-4">
                                <input type="email" class="form-control" placeholder="Email or Username" value="wrapcode.info@gmail.com" required>
                            </div>
                            <div class="mb-3">
                                <input type="password" class="form-control" placeholder="Password" value="123456" required>
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
</body>

</html>