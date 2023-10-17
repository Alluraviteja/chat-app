<div class="home-center">
    <div class="home-desc-center">

        <div class="container">
            <div class="row justify-content-end">
                <div class="col-md-8 col-lg-6 col-xl-5">
                    <div class="card">
                        <div class="card-body">
                            <div class="px-2 py-3">


                                <div class="text-center">
                                    <h4>
                                        <a href="javascript:void(0)">
                                           Raviteja Portfolio
                                        </a>
                                    </h4>

                                    <h5 class="text-primary mb-2 mt-4">Welcome Back !</h5>
                                    <p class="text-muted">Sign in to continue</p>
                                </div>

								
                                <form class="form-horizontal mt-4 pt-2 needs-validation-login-request" novalidate>

                                    <div class="mb-3">
                                        <label for="username">Username/Email</label>
                                        <input type="text" class="form-control" id="username" required
                                            placeholder="Enter username">
                                       	<div class="invalid-feedback">Please enter Username</div>
                                    </div>

                                    <div class="mb-3">
                                        <label for="userpassword">Password</label>
                                        <input type="password" class="form-control" id="password" required
                                            placeholder="Enter password">
                                       	<div class="invalid-feedback">Please enter password</div>
                                    </div>
									
                                    <div>
                                        <button class="btn btn-primary w-100 waves-effect waves-light"
                                            type="submit" id="login-request-form">Log In
                                        </button>
                                    </div>

                                    <div class="mt-4 text-center">
                                        <a href="${pageContext.request.contextPath}/forgotPassword" class="text-muted">
                                        	<i class="mdi mdi-lock me-1"></i> Forgot your password?
                                       	</a>
                                    </div>


                                </form>

                              
                            </div>
                        </div>
                    </div>

                    <div class="mt-5 text-center text-white">
                        <p>Don't have an account ?
                        	<a href="${pageContext.request.contextPath}/signUp" class="fw-bold text-white"> Register</a>
                       	</p>
                    </div>
                </div>
            </div>

        </div>


    </div>
    <!-- End Log In page -->
</div>

<script src="${pageContext.request.contextPath}/functionJs/forgotPasswordFunction.js?v1.0"></script>
