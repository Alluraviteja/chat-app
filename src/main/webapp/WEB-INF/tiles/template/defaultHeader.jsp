<header id="page-topbar">
    <div class="navbar-header">
        <div class="d-flex">

               <!-- LOGO -->
         <div class="navbar-brand-box">
            <a href="javascript:void(0)" class="logo logo-dark">
                <span class="logo-sm" id="loginUserNameMenuSmall"></span>
                <span class="logo-lg" id="loginUserNameMenuLarge"></span>
            </a>
        </div>

            <button type="button" class="btn btn-sm px-3 font-size-24 header-item waves-effect" id="vertical-menu-btn">
                <i class="mdi mdi-menu"></i>
            </button>            
        </div>

        <div class="d-flex">
           	<div class="dropdown d-none d-lg-inline-block ms-1">
                <button type="button" class="btn header-item noti-icon waves-effect" data-toggle="fullscreen">
                    <i class="mdi mdi-fullscreen"></i>
                </button>
            </div>
			
            <div class="dropdown d-inline-block">
                <button type="button" class="btn header-item waves-effect" id="page-header-user-dropdown"
                    data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <img class="rounded-circle header-profile-user" src="assets/images/avatar-7.jpg"
                        alt="Header Avatar">
                    <span class="d-none d-xl-inline-block ms-1" id="loginUserNameNotification"></span>
                    <i class="mdi mdi-chevron-down d-none d-xl-inline-block"></i>
                </button>
                <div class="dropdown-menu dropdown-menu-end">
                    <!-- item-->
                    <!-- <a class="dropdown-item" href="javascript:void(0)"><i class="mdi mdi-account-circle-outline font-size-16 align-middle me-1"></i> Profile</a>
                    <a class="dropdown-item d-block" href="javascript:void(0)"><span class="badge badge-success float-end">11</span><i class="mdi mdi-cog-outline font-size-16 align-middle me-1"></i> Settings</a>
                    <a class="dropdown-item" href="javascript:void(0)"><i class="mdi mdi-lock-open-outline font-size-16 align-middle me-1"></i> Lock screen</a>
                    <div class="dropdown-divider"></div> -->
                    <a class="dropdown-item text-danger" href="javascript:void(0)" id="logoutButton">
                    	<i class="mdi mdi-power font-size-16 align-middle me-1 text-danger"></i> Logout
                   	</a>
                </div>
            </div>            
        </div>
    </div>
</header>