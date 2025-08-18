<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@page import="com.hp.model.AuthorshipDetails"%>
<%@page import="com.hp.model.WritingDetails"%>
<%@page import="com.hp.model.ArticleDetails"%>
<%@page import="com.hp.model.ContactDetails"%>
<%@page import="java.util.List"%>
<html lang="zxx">

<head>
    <jsp:include page="../css.jsp"></jsp:include>
    <style type="text/css">
    	.paid-row {
		  background-color: #00ff00 !important; /* light green */
		}
		
		.partially-paid-row {
		  background-color: yellow !important; /* light yellow */
		}
		
	.pending-row {
		 background-color: red !important; /* light red */
	}
	.acceptance-row {
		 background-color: #ADD8E6 !important; /* light red */
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
    .sttuss{
	    border: 1px solid lightgray;
	    padding: 8px;
	    border-radius: 3px;
	    cursor: pointer;
	}
	.sttuss.active {
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
            <!-- [ page-header ] start -->
            <div class="page-header">
                <div class="page-header-left d-flex align-items-center">
                    <div class="page-header-title">
                        <h5 class="m-b-10">Dashboard</h5>
                    </div>
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb-item">Dashboard</li>
                    </ul>
                </div>
                <div class="page-header-right ms-auto">
                    <div class="page-header-right-items">
                        <div class="d-flex d-md-none">
                            <a href="javascript:void(0)" class="page-header-right-close-toggle">
                                <i class="feather-arrow-left me-2"></i>
                                <span>Back</span>
                            </a>
                        </div>
                        <div class="d-flex align-items-center gap-2 page-header-right-items-wrapper">
                            <div id="reportrange" class="reportrange-picker d-flex align-items-center">
                                <span class="reportrange-picker-field"></span>
                            </div>
                            <div class="dropdown filter-dropdown" style="display: none;">
                                <a class="btn btn-md btn-light-brand" data-bs-toggle="dropdown" data-bs-offset="0, 10" data-bs-auto-close="outside">
                                    <i class="feather-filter me-2"></i>
                                    <span>Filter</span>
                                </a>
                                <div class="dropdown-menu dropdown-menu-end">
                                    <div class="dropdown-item">
                                    </div>
                                    <div class="dropdown-item">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="Team" checked="checked" />
                                            <label class="custom-control-label c-pointer" for="Team">Team</label>
                                        </div>
                                    </div>
                                    <div class="dropdown-item">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="Email" checked="checked" />
                                            <label class="custom-control-label c-pointer" for="Email">Email</label>
                                        </div>
                                    </div>
                                    <div class="dropdown-item">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="Member" checked="checked" />
                                            <label class="custom-control-label c-pointer" for="Member">Member</label>
                                        </div>
                                    </div>
                                    <div class="dropdown-item">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="Recommendation" checked="checked" />
                                            <label class="custom-control-label c-pointer" for="Recommendation">Recommendation</label>
                                        </div>
                                    </div>
                                    <div class="dropdown-divider"></div>
                                    <a href="javascript:void(0);" class="dropdown-item">
                                        <i class="feather-plus me-3"></i>
                                        <span>Create New</span>
                                    </a>
                                    <a href="javascript:void(0);" class="dropdown-item">
                                        <i class="feather-filter me-3"></i>
                                        <span>Manage Filter</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="d-md-none d-flex align-items-center">
                        <a href="javascript:void(0)" class="page-header-right-open-toggle">
                            <i class="feather-align-right fs-20"></i>
                        </a>
                    </div>
                </div>
            </div>
            <!-- [ page-header ] end -->
            <!-- [ Main Content ] start -->
            <div class="main-content">
                <div class="row">
                 <!-- [Converted Leads] start -->
                    <div class="col-xxl-3 col-md-6">
                        <div class="card stretch stretch-full">
                            <div class="card-body">
                                <div class="d-flex align-items-start justify-content-between mb-4">
                                    <div class="d-flex gap-4 align-items-center">
                                        <div class="avatar-text avatar-lg bg-gray-200">
                                            <i class="feather-cast"></i>
                                        </div>
                                        <div>
                                            <div class="fs-4 fw-bold text-dark"><span class="counter" id="con"></span>/<span class="counter" id="tot"></span></div>
                                            <h3 class="fs-13 fw-semibold text-truncate-1-line">Converted Leads</h3>
                                        </div>
                                    </div>
                                    <a href="javascript:void(0);" class="">
                                        <i class="feather-more-vertical"></i>
                                    </a>
                                </div>
                                <div class="pt-4">
                                    <div class="d-flex align-items-center justify-content-between">
                                        <a href="javascript:void(0);" class="fs-12 fw-medium text-muted text-truncate-1-line">Converted Leads </a>
                                       <div class="w-100 text-end">
                                            <span class="fs-11 text-muted" id="conper"></span>
                                        </div>
                                    </div>
                                    <div class="progress mt-2 ht-3">
                                        <div class="progress-bar bg-warning" role="progressbar" id="conpro"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- [Converted Leads] end -->
                    <!-- [Invoices Awaiting Payment] start -->
                    <div class="col-xxl-3 col-md-6">
                        <div class="card stretch stretch-full">
                            <div class="card-body">
                                <div class="d-flex align-items-start justify-content-between mb-4">
                                    <div class="d-flex gap-4 align-items-center">
                                        <div class="avatar-text avatar-lg bg-gray-200">
                                            <i class="feather-dollar-sign"></i>
                                        </div>
                                        <div>
                                            <div class="fs-4 fw-bold text-dark"><span class="counter" id="cpcon"></span>/<span class="counter" id="pcon"></span></div>
                                            <h3 class="fs-13 fw-semibold text-truncate-1-line">Publication In Progress</h3>
                                        </div>
                                    </div>
                                    <a href="javascript:void(0);" class="">
                                        <i class="feather-more-vertical"></i>
                                    </a>
                                </div>
                                <div class="pt-4">
                                    <div class="d-flex align-items-center justify-content-between">
                                        <a href="javascript:void(0);" class="fs-12 fw-medium text-muted text-truncate-1-line">Progress... </a>
                                        <div class="w-100 text-end">
                                            <span class="fs-11 text-muted" id="pper"></span>
                                        </div>
                                    </div>
                                    <div class="progress mt-2 ht-3">
                                        <div class="progress-bar bg-primary" role="progressbar" id="ppro"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- [Invoices Awaiting Payment] end -->
                    <!-- [Projects In Progress] start -->
                    <div class="col-xxl-3 col-md-6">
                        <div class="card stretch stretch-full">
                            <div class="card-body">
                                <div class="d-flex align-items-start justify-content-between mb-4">
                                    <div class="d-flex gap-4 align-items-center">
                                        <div class="avatar-text avatar-lg bg-gray-200">
                                            <i class="feather-briefcase"></i>
                                        </div>
                                        <div>
                                            <div class="fs-4 fw-bold text-dark"><span class="counter" id="cwcon"></span>/<span class="counter" id="wcon"></span></div>
                                            <h3 class="fs-13 fw-semibold text-truncate-1-line">Writing Paper In Progress</h3>
                                        </div>
                                    </div>
                                    <a href="javascript:void(0);" class="">
                                        <i class="feather-more-vertical"></i>
                                    </a>
                                </div>
                                <div class="pt-4">
                                    <div class="d-flex align-items-center justify-content-between">
                                        <a href="javascript:void(0);" class="fs-12 fw-medium text-muted text-truncate-1-line">Progress... </a>
                                        <div class="w-100 text-end">
                                            <span class="fs-11 text-muted" id="wper"></span>
                                        </div>
                                    </div>
                                    <div class="progress mt-2 ht-3">
                                        <div class="progress-bar bg-success" role="progressbar" id="wpro" ></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- [Projects In Progress] end -->
                    <!-- [Conversion Rate] start -->
                    <div class="col-xxl-3 col-md-6">
                        <div class="card stretch stretch-full">
                            <div class="card-body">
                                <div class="d-flex align-items-start justify-content-between mb-4">
                                    <div class="d-flex gap-4 align-items-center">
                                        <div class="avatar-text avatar-lg bg-gray-200">
                                            <i class="feather-activity"></i>
                                        </div>
                                        <div>
                                            <div class="fs-4 fw-bold text-dark"><span class="counter" id="cacon"></span>/<span class="counter" id="acon"></span></div>
                                            <h3 class="fs-13 fw-semibold text-truncate-1-line">Authorship In Progress</h3>
                                        </div>
                                    </div>
                                    <a href="javascript:void(0);" class="">
                                        <i class="feather-more-vertical"></i>
                                    </a>
                                </div>
                                <div class="pt-4">
                                    <div class="d-flex align-items-center justify-content-between">
                                        <a href="javascript:void(0);" class="fs-12 fw-medium text-muted text-truncate-1-line"> Progress...</a>
                                       <div class="w-100 text-end">
                                            <span class="fs-11 text-muted" id="aper"></span>
                                        </div>
                                    </div>
                                    <div class="progress mt-2 ht-3">
                                        <div class="progress-bar bg-danger" role="progressbar" id="apro"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- [Conversion Rate] end -->
                     <!-- [Project Records] start -->
                    <div class="col-xxl-12">
                        <div class="card stretch stretch-full">
                            <div class="card-header">
                                <h5 class="card-title">Project Progress Report</h5>
                                <div class="card-header-action">
                                    <div class="card-header-btn">
                                        <div data-bs-toggle="tooltip" title="Refresh">
                                            <a href="javascript:void(0);" class="avatar-text avatar-xs bg-warning" data-bs-toggle="refresh"> </a>
                                        </div>
                                        <div data-bs-toggle="tooltip" title="Maximize/Minimize">
                                            <a href="javascript:void(0);" class="avatar-text avatar-xs bg-success" data-bs-toggle="expand"> </a>
                                        </div>
                                    </div>
                                    <div class="dropdown">
                                        <a href="javascript:void(0);" class="avatar-text avatar-sm" data-bs-toggle="dropdown" data-bs-offset="25, 25">
                                            <div data-bs-toggle="tooltip" title="Options">
                                                <i class="feather-more-vertical"></i>
                                            </div>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-end">
                                            <a href="javascript:void(0);" class="dropdown-item sttuss">Publication</a>
                                            <div class="dropdown-divider"></div>
                                            <a href="javascript:void(0);" class="dropdown-item sttuss">Writing Paper</a>
                                            <div class="dropdown-divider"></div>
                                            <a href="javascript:void(0);" class="dropdown-item sttuss">Authorship</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body custom-card-action p-0">
                                <div class="table-responsive">
									<table id="project_table" class="table table-striped nowrap display responsive" style="width:100%">
										<thead class="bg-primary">
											<tr>
												<th class="text-white">Article Id</th>
												<th class="text-white">Contact Number</th>
												<th class="text-white">email</th>
												<th class="text-white">Total Amount</th>
												<th class="text-white">Journal Name</th>
												<th class="text-white">Title</th>
												<th class="text-white">Module</th>
												<!-- <th class="text-white">Status</th> -->
											</tr>
										</thead>
									</table>
								</div>
                            </div>
                        </div>
                    </div>
                    <!-- [Project Records] end -->
                     <input type="hidden" id="from_date" name="from_date">
    				 <input type="hidden" id="to_date" name="to_date">
                    <!-- [Payment Records] start -->
                    <div class="col-xxl-12">
                        <div class="card stretch stretch-full">
                            <div class="card-header">
                                <h5 class="card-title">Payment Record</h5>
                                <div class="card-header-action">
                                    <div class="card-header-btn">
                                        <div data-bs-toggle="tooltip" title="Refresh">
                                            <a href="javascript:void(0);" class="avatar-text avatar-xs bg-warning" data-bs-toggle="refresh"> </a>
                                        </div>
                                        <div data-bs-toggle="tooltip" title="Maximize/Minimize">
                                            <a href="javascript:void(0);" class="avatar-text avatar-xs bg-success" data-bs-toggle="expand"> </a>
                                        </div>
                                    </div>
                                    <div class="dropdown">
                                        <a href="javascript:void(0);" class="avatar-text avatar-sm" data-bs-toggle="dropdown" data-bs-offset="25, 25">
                                            <div data-bs-toggle="tooltip" title="Options">
                                                <i class="feather-more-vertical"></i>
                                            </div>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-end">
                                            <a href="javascript:void(0);" class="dropdown-item sttus">Paid</a>
                                            <div class="dropdown-divider"></div>
                                            <a href="javascript:void(0);" class="dropdown-item sttus">Partially Paid</a>
                                            <div class="dropdown-divider"></div>
                                            <a href="javascript:void(0);" class="dropdown-item sttus">Pending</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body custom-card-action p-0">
                                <div class="table-responsive">
									<table id="payment_table" class="table table-striped nowrap display responsive" style="width:100%">
										<thead class="bg-primary">
											<tr>
												<th class="text-white">Article Id</th>
												<th class="text-white">Contact Number</th>
												<th class="text-white">email</th>
												<th class="text-white">Total Amount</th>
												<th class="text-white">Remaining Amount</th>
												<th class="text-white">Journal Name</th>
												<th class="text-white">Article Title</th>
												<th class="text-white">Module</th>
												<th class="text-white">Status</th>
											</tr>
										</thead>
									</table>
								</div>
                            </div>
                        </div>
                    </div>
                    <!-- [Payment Records] end -->
                    <!-- [Article Record] start -->
                    <div class="col-xxl-12">
                        <div class="card stretch stretch-full">
                            <div class="card-header">
                                <h5 class="card-title">Authorship Article Details</h5>
                                <div class="card-header-action">
                                    <div class="card-header-btn">
                                        <div data-bs-toggle="tooltip" title="Refresh">
                                            <a href="javascript:void(0);" class="avatar-text avatar-xs bg-warning" data-bs-toggle="refresh"> </a>
                                        </div>
                                        <div data-bs-toggle="tooltip" title="Maximize/Minimize">
                                            <a href="javascript:void(0);" class="avatar-text avatar-xs bg-success" data-bs-toggle="expand"> </a>
                                        </div>
                                    </div>
                                    <div class="dropdown">
                                        <a href="javascript:void(0);" class="avatar-text avatar-sm" data-bs-toggle="dropdown" data-bs-offset="25, 25">
                                            <div data-bs-toggle="tooltip" title="Options">
                                                <i class="feather-more-vertical"></i>
                                            </div>
                                        </a>
                                        <!-- <div class="dropdown-menu dropdown-menu-end">
                                            <a href="javascript:void(0);" class="dropdown-item sttus">Paid</a>
                                            <div class="dropdown-divider"></div>
                                            <a href="javascript:void(0);" class="dropdown-item sttus">Partially Paid</a>
                                            <div class="dropdown-divider"></div>
                                            <a href="javascript:void(0);" class="dropdown-item sttus">Pending</a>
                                        </div> -->
                                    </div>
                                </div>
                            </div>
                            <div class="card-body custom-card-action p-0">
                                <div class="accordion" id="accordian_item">
								 
								  
								</div>
                            </div>
                        </div>
                    </div>
                    <!-- [Payment Records] end -->
                </div>
            </div>
            <!-- [ Main Content ] end -->
        </div>
        <!-- [ Footer ] start -->
       <jsp:include page="../footer.jsp"></jsp:include>
        <!-- [ Footer ] end -->
    </main>
    <!--! ================================================================ !-->
    <!--! [End] Main Content !-->
    <!--! ================================================================ !-->
    <!--! ================================================================ !-->
    <!--! BEGIN: Theme Customizer !-->
    <!--! ================================================================ !-->
    <div class="theme-customizer">
        <div class="customizer-handle">
            <a href="javascript:void(0);" class="cutomizer-open-trigger bg-primary">
                <i class="feather-settings"></i>
            </a>
        </div>
        <div class="customizer-sidebar-wrapper">
            <div class="customizer-sidebar-header px-4 ht-80 border-bottom d-flex align-items-center justify-content-between">
                <h5 class="mb-0">Theme Settings</h5>
                <a href="javascript:void(0);" class="cutomizer-close-trigger d-flex">
                    <i class="feather-x"></i>
                </a>
            </div>
            <div class="customizer-sidebar-body position-relative p-4" data-scrollbar-target="#psScrollbarInit">
                <!--! BEGIN: [Navigation] !-->
                <div class="position-relative px-3 pb-3 pt-4 mt-3 mb-5 border border-gray-2 theme-options-set">
                    <label class="py-1 px-2 fs-8 fw-bold text-uppercase text-muted text-spacing-2 bg-white border border-gray-2 position-absolute rounded-2 options-label" style="top: -12px">Navigation</label>
                    <div class="row g-2 theme-options-items app-navigation" id="appNavigationList">
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-navigation-light" name="app-navigation" value="1" data-app-navigation="app-navigation-light" checked />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-navigation-light">Light</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-navigation-dark" name="app-navigation" value="2" data-app-navigation="app-navigation-dark" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-navigation-dark">Dark</label>
                        </div>
                    </div>
                </div>
                <!--! END: [Navigation] !-->
                <!--! BEGIN: [Header] !-->
                <div class="position-relative px-3 pb-3 pt-4 mt-3 mb-5 border border-gray-2 theme-options-set mt-5">
                    <label class="py-1 px-2 fs-8 fw-bold text-uppercase text-muted text-spacing-2 bg-white border border-gray-2 position-absolute rounded-2 options-label" style="top: -12px">Header</label>
                    <div class="row g-2 theme-options-items app-header" id="appHeaderList">
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-header-light" name="app-header" value="1" data-app-header="app-header-light" checked />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-header-light">Light</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-header-dark" name="app-header" value="2" data-app-header="app-header-dark" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-header-dark">Dark</label>
                        </div>
                    </div>
                </div>
                <!--! END: [Header] !-->
                <!--! BEGIN: [Skins] !-->
                <div class="position-relative px-3 pb-3 pt-4 mt-3 mb-5 border border-gray-2 theme-options-set">
                    <label class="py-1 px-2 fs-8 fw-bold text-uppercase text-muted text-spacing-2 bg-white border border-gray-2 position-absolute rounded-2 options-label" style="top: -12px">Skins</label>
                    <div class="row g-2 theme-options-items app-skin" id="appSkinList">
                        <div class="col-6 text-center position-relative single-option light-button active">
                            <input type="radio" class="btn-check" id="app-skin-light" name="app-skin" value="1" data-app-skin="app-skin-light" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-skin-light">Light</label>
                        </div>
                        <div class="col-6 text-center position-relative single-option dark-button">
                            <input type="radio" class="btn-check" id="app-skin-dark" name="app-skin" value="2" data-app-skin="app-skin-dark" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-skin-dark">Dark</label>
                        </div>
                    </div>
                </div>
                <!--! END: [Skins] !-->
                <!--! BEGIN: [Typography] !-->
                <div class="position-relative px-3 pb-3 pt-4 mt-3 mb-0 border border-gray-2 theme-options-set">
                    <label class="py-1 px-2 fs-8 fw-bold text-uppercase text-muted text-spacing-2 bg-white border border-gray-2 position-absolute rounded-2 options-label" style="top: -12px">Typography</label>
                    <div class="row g-2 theme-options-items font-family" id="fontFamilyList">
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-lato" name="font-family" value="1" data-font-family="app-font-family-lato" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-lato">Lato</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-rubik" name="font-family" value="2" data-font-family="app-font-family-rubik" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-rubik">Rubik</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-inter" name="font-family" value="3" data-font-family="app-font-family-inter" checked />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-inter">Inter</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-cinzel" name="font-family" value="4" data-font-family="app-font-family-cinzel" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-cinzel">Cinzel</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-nunito" name="font-family" value="6" data-font-family="app-font-family-nunito" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-nunito">Nunito</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-roboto" name="font-family" value="7" data-font-family="app-font-family-roboto" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-roboto">Roboto</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-ubuntu" name="font-family" value="8" data-font-family="app-font-family-ubuntu" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-ubuntu">Ubuntu</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-poppins" name="font-family" value="9" data-font-family="app-font-family-poppins" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-poppins">Poppins</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-raleway" name="font-family" value="10" data-font-family="app-font-family-raleway" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-raleway">Raleway</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-system-ui" name="font-family" value="11" data-font-family="app-font-family-system-ui" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-system-ui">System UI</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-noto-sans" name="font-family" value="12" data-font-family="app-font-family-noto-sans" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-noto-sans">Noto Sans</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-fira-sans" name="font-family" value="13" data-font-family="app-font-family-fira-sans" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-fira-sans">Fira Sans</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-work-sans" name="font-family" value="14" data-font-family="app-font-family-work-sans" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-work-sans">Work Sans</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-open-sans" name="font-family" value="15" data-font-family="app-font-family-open-sans" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-open-sans">Open Sans</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-maven-pro" name="font-family" value="16" data-font-family="app-font-family-maven-pro" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-maven-pro">Maven Pro</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-quicksand" name="font-family" value="17" data-font-family="app-font-family-quicksand" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-quicksand">Quicksand</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-montserrat" name="font-family" value="18" data-font-family="app-font-family-montserrat" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-montserrat">Montserrat</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-josefin-sans" name="font-family" value="19" data-font-family="app-font-family-josefin-sans" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-josefin-sans">Josefin Sans</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-ibm-plex-sans" name="font-family" value="20" data-font-family="app-font-family-ibm-plex-sans" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-ibm-plex-sans">IBM Plex Sans</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-source-sans-pro" name="font-family" value="5" data-font-family="app-font-family-source-sans-pro" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-source-sans-pro">Source Sans Pro</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-montserrat-alt" name="font-family" value="21" data-font-family="app-font-family-montserrat-alt" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-montserrat-alt">Montserrat Alt</label>
                        </div>
                        <div class="col-6 text-center single-option">
                            <input type="radio" class="btn-check" id="app-font-family-roboto-slab" name="font-family" value="22" data-font-family="app-font-family-roboto-slab" />
                            <label class="py-2 fs-9 fw-bold text-dark text-uppercase text-spacing-1 border border-gray-2 w-100 h-100 c-pointer position-relative options-label" for="app-font-family-roboto-slab">Roboto Slab</label>
                        </div>
                    </div>
                </div>
                <!--! END: [Typography] !-->
            </div>
            <div class="customizer-sidebar-footer px-4 ht-60 border-top d-flex align-items-center gap-2">
                <div class="flex-fill w-50">
                    <a href="javascript:void(0);" class="btn btn-danger" data-style="reset-all-common-style">Reset</a>
                </div>
                <div class="flex-fill w-50">
                    <a href="javascript:void(0);" class="btn btn-primary">Download</a>
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
   
   <jsp:include page="../js.jsp"></jsp:include>
   
   <script type="text/javascript">
   let employee_id = $("#employee_id").val();
   
   $("#reportrange").on("click", ".ranges ul li", function () {
	   // Small delay to let daterangepicker update the date range display & hidden inputs
	   setTimeout(() => {
	     var fromDate = $("#from_date").val();
	     var toDate = $("#to_date").val();

	     console.log("Range clicked, from:", fromDate, "to:", toDate);

	     // Call your functions here
	     pdata();
	     pdata1();
	     getDetails();
	     getNotification();
	   }, 10);
	 });
  
   function pdata() {
	   // ✅ Destroy existing DataTable instance if it exists
	   if ($.fn.DataTable.isDataTable("#payment_table")) {
	     $('#payment_table').DataTable().clear().destroy();
	   }

	   $("#payment_table").DataTable({
	     dom: "Blfrtip",
	     autoWidth: true,
	     responsive: true,
	     buttons: [
	       { extend: 'pdf', exportOptions: { columns: ':visible' } },
	       { extend: 'csv', exportOptions: { columns: ':visible' } },
	       { extend: 'print', exportOptions: { columns: ':visible' } },
	       { extend: 'excel', exportOptions: { columns: ':visible' } },
	       { extend: 'pageLength' }
	     ],
	     lengthChange: true,
	     ordering: false,
	     ajax: {
	       url: "get_payment",
	       type: "POST",
	       data: function (d) {
	         const from_date = $("#from_date").val();
	         const to_date = $("#to_date").val();

	         return {
	           ...d,
	           employee_id: employee_id,
	           from_date: from_date,
	           to_date: to_date
	         };
	       }
	     },
	     columnDefs: [{
	       defaultContent: "-",
	       targets: "_all"
	     }],
	     serverSide: true,
	     columns: [
	       { data: "article_id" },
	       { data: "contact_number" },
	       { data: "email" },
	       { data: "total_amount" },
	       { data: "remaining_amount" },
	       { data: "journal_name" },
	       { data: "title" },
	       { data: "module" },
	       { data: "payment_status" }
	     ],
	     lengthMenu: [[5, 10, 25, 50], [5, 10, 25, 50]],
	     select: true,
	     createdRow: function (row, data) {
	       if (!data.payment_status) return;

	       let status = data.payment_status.toLowerCase().trim();
	       if (status === 'paid') {
	         $(row).addClass('paid-row');
	       } else if (status === 'partially paid') {
	         $(row).addClass('partially-paid-row');
	       } else if (status === 'pending') {
	         $(row).addClass('pending-row');
	       }
	     }
	   });
	 }

	 pdata(); // Call it once initially
   function pdata1() {
	   // ✅ Destroy existing DataTable instance if it exists
	   if ($.fn.DataTable.isDataTable("#project_table")) {
	     $('#project_table').DataTable().clear().destroy();
	   }

	   $("#project_table").DataTable({
	     dom: "Blfrtip",
	     autoWidth: true,
	     responsive: true,
	     buttons: [
	       { extend: 'pdf', exportOptions: { columns: ':visible' } },
	       { extend: 'csv', exportOptions: { columns: ':visible' } },
	       { extend: 'print', exportOptions: { columns: ':visible' } },
	       { extend: 'excel', exportOptions: { columns: ':visible' } },
	       { extend: 'pageLength' }
	     ],
	     lengthChange: true,
	     ordering: false,
	     ajax: {
	       url: "get_project_data",
	       type: "POST",
	       data: function (d) {
	    	   const from_date = $("#from_date").val();
		         const to_date = $("#to_date").val();

	         return {
	           ...d,
	           employee_id: employee_id,
	           from_date: from_date,
	           to_date: to_date
	         };
	       }
	     },
	     columnDefs: [{
	       defaultContent: "-",
	       targets: "_all"
	     }],
	     serverSide: true,
	     columns: [
	       { data: "article_id" },
	       { data: "contact_number" },
	       { data: "email" },
	       { data: "amount" },
	       { data: "journal_name" },
	       { data: "title" },
	       { data: "module" },
	      /*  { data: "status" } */
	     ],
	     lengthMenu: [[5, 10, 25, 50], [5, 10, 25, 50]],
	     select: true,
	     createdRow: function (row, data) {
	       if (!data.status) return;
	       let module = data.module.toLowerCase().trim();
	       let status = data.status.toLowerCase().trim();
	       if (module === 'publication') {
	    	   	if(status == "acceptance"){
	    	   		$(row).addClass('acceptance-row');
	    	   	}else if(status == "received" || status == "partially paid" || status == "proved" || status == "paid"){
	    	   		$(row).addClass('partially-paid-row');
	    	   	}else if(status == "published"){
	    	   	 $(row).addClass('paid-row');
	    	   	}
	       } else if (module === 'writing paper') {
	    		if(status == "received" || status == "confirmed" || status == "sent confirmation" || status == "paid" || status == "full work confirmation"){
	    	   		$(row).addClass('partially-paid-row');
	    	   	}else if(status == "completed"){
	    	   	 $(row).addClass('paid-row');
	    	   	}
	       } else if (module === 'authorship') {
	    		if(status == "received" || status == "initiated"){
	    			$(row).addClass('partially-paid-row');
	    	   	}else if(status == "acceptance" || status == "partially" || status == "proved"){
	    	   		$(row).addClass('acceptance-row');
	    	   	}else if(status == "completed"){
	    	   	 $(row).addClass('paid-row');
	    	   	}
	       }
	     }
	   });
}

	 pdata1(); // Call it once initially



	 function tabledata(status) {
		    $("#payment_table").DataTable().clear().destroy(); // Destroy previous instance

		    $("#payment_table").DataTable({
			     dom: "Blfrtip",
			     autoWidth: true,
			     responsive: true,
			     buttons: [
			       { extend: 'pdf', exportOptions: { columns: ':visible' } },
			       { extend: 'csv', exportOptions: { columns: ':visible' } },
			       { extend: 'print', exportOptions: { columns: ':visible' } },
			       { extend: 'excel', exportOptions: { columns: ':visible' } },
			       { extend: 'pageLength' }
			     ],
			     lengthChange: true,
			     ordering: false,
			     ajax: {
			       url: "get_payment",
			       type: "POST",
			       data: function(d) {
			    	   const from_date = $("#from_date").val();
				         const to_date = $("#to_date").val();

			         return {
			           ...d,
			           employee_id: employee_id,
			           status: status,
			           from_date: from_date,
			           to_date: to_date
			         };
			       }
			     },
			     columnDefs: [{
			       defaultContent: "-",
			       targets: "_all"
			     }],
			     serverSide: true,
			     columns: [
			       { data: "article_id" },
			       { data: "contact_number" },
			       { data: "email" },
			       { data: "total_amount" },
			       { data: "remaining_amount" },
			       { data: "journal_name" },
			       { data: "title" },
			       { data: "module" },
			       { data: "payment_status" }
			     ],
			     lengthMenu: [[5, 10, 25, 50], [5, 10, 25, 50]],
			     select: true,
			     createdRow: function(row, data) {
			       if (!data.payment_status) return;

			       let status = data.payment_status.toLowerCase().trim();
			       if (status === 'paid') {
			         $(row).addClass('paid-row');
			       } else if (status === 'partially paid') {
			         $(row).addClass('partially-paid-row');
			       } else if (status === 'pending') {
			         $(row).addClass('pending-row');
			       }
			     }
			   });
		}
		$(document).on('click', '.sttus', function () {
		    var status = $(this).text().trim();

		    // Remove 'active' class from all, then add to clicked
		    $('.sttus').removeClass('active');
		    $(this).addClass('active');

		    // Call data with selected status
		    tabledata(status);
		});

		function getDetails() {
			  var from_date = $("#from_date").val();
			  var to_date = $("#to_date").val();
			  var fd = new FormData();
			  fd.append("employee_id", employee_id);
			  fd.append("from_date", from_date);
			  fd.append("to_date", to_date);
			  
			  $.ajax({
			    url: 'get_details',
			    type: 'post',
			    data: fd,
			    contentType: false,
			    processData: false,
			    success: function(data) {
			      if (data['status'] == 'Success') {
			        $("#tot").html(data.total);
			        $("#con").html(data.converted);
			        $("#conper").html(data.conv_percent.toFixed(2) + "%");
			        $("#conpro").css("width", data.conv_percent.toFixed(2) + "%");
			        $("#pcon").html(data.pub_conv);
			        $("#wcon").html(data.write_conv);
			        $("#acon").html(data.auth_conv);
			        $("#cpcon").html(data.articles);
			        $("#cwcon").html(data.writings);
			        $("#cacon").html(data.authorships);
			        $("#pper").html(data.pub_percent.toFixed(2) + "%");
			        $("#ppro").css("width", data.pub_percent.toFixed(2) + "%");
			        $("#wper").html(data.write_percent.toFixed(2) + "%");
			        $("#wpro").css("width", data.write_percent.toFixed(2) + "%");
			        $("#aper").html(data.auth_percent.toFixed(2) + "%");
			        $("#apro").css("width", data.auth_percent.toFixed(2) + "%");
			      }
			    }
			  });
			}

	
		getDetails();
		function getjournalDetails() {
			  $("#accordian_item").html("");
			  var from_date = $("#from_date").val();
			  var to_date = $("#to_date").val();
			  var fd = new FormData();
			  fd.append("from_date", from_date);
			  fd.append("to_date", to_date);
			  
			  $.ajax({
			    url: 'get_article_details',
			    type: 'post',
			    data: fd,
			    contentType: false,
			    processData: false,
			    success: function(data) {
			      if (data['status'] == 'Success') {
			    	  var html = "";
			    	 for(var i=0; i < data['journal_name'].length; i++){
			    		html += '<div class="accordion-item">' +
			             '<h2 class="accordion-header" id="panelsStayOpen-headingOne'+i+'">' +
			               '<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne'+i+'" onclick="getaccordiandata(\'' + data["journal_name"][i] + '\',' + i + ')" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne'+i+'" style="background: white;">' +
			                 ''+data['journal_name'][i]+'' +
			               '</button>' +
			             '</h2>' +
			             '<div id="panelsStayOpen-collapseOne'+i+'" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingOne'+i+'">' +
			               '<div class="accordion-body">' +
			               	'<div class="row" id="position'+i+'">'+
			               	'</div>'+
			               '</div>' +
			             '</div>' +
			           '</div>';
			    	 }
			    	 $("#accordian_item").html(html);
			      }
			    }
			  });
			}

	
		getjournalDetails();
		
		function getaccordiandata(journal_name,p) {
		    $('#positionview_modal').modal('toggle');
		    var from_date = $("#from_date").val();
		    var to_date = $("#to_date").val();
		    var fd = new FormData();
		    fd.append("journal_name", journal_name);
		    fd.append("from_date", from_date);
		    fd.append("to_date", to_date);

		    $.ajax({
		        url: 'get_positions_details',
		        type: 'post',
		        data: fd,
		        contentType: false,
		        processData: false,
		        success: function(data) {
		            if (data['status'] === 'Success') {
		                var html = '';
		                $('#position'+p).html("");
		               
		                for (var j = 0; j < data['data'].length; j++) {
		                	 var sum = 0;
				                var col = 0;
		                	 var ap = data['data'][j].ap;
		               	html += '<div class="col-md-6" style="border: 1px solid lightgray;">';
		                html += '<h6 class="card-title text-primary fw-bold m-3" id="atitle">'+data['data'][j].title+'</h6>';
		                html += '<p class="text-muted mb-3 mx-3" id="ajournal"><em>Journal Name: '+data['data'][j].journal_name+'</em></p>';
		                html += '<div class="table-responsive">';
		                html += '<table class="table table-bordered table-hover align-middle text-center">';
		                html += '<thead class="table-primary">';
		                html += '<tr>';
		                html += '<th>Position</th>';
		                html += '<th>Price</th>';
		                html += '<th>Status</th>';
		                html += '<th>Booking Price</th>';
		                html += '<th>Booked By</th>';
		                html += '</tr>';
		                html += '</thead>';
		                html += '<tbody>';

		                for (var i = 0; i < ap.length; i++) {
		                    sum += parseFloat(ap[i].position_amount);
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
		                        col += parseFloat(ap[i].booked_amount);
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

		                html += '<tr class="table-primary">';
		                html += '<td><strong>Total:</strong></td>';
		                html += '<td class="price-tag">₹' + sum + '</td>';
		                html += '<td><strong>Collected</strong></td>';
		                html += '<td><strong>₹' + col + '</strong></td>';
		                html += '<td>-</td>';
		                html += '</tr>';

		                html += '</tbody>';
		                html += '</table>';
		                html += '</div>';
		                html += '</div>';
		                }
		                $('#position'+p).html(html); // Replace previous content
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