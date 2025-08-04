<!--! ================================================================ !-->
    <!--! [Start] Navigation Manu !-->
    <!--! ================================================================ !-->
  <%@page import="com.hp.model.LoginCredentials"%>
<%
  LoginCredentials log = (LoginCredentials)session.getAttribute("loginData");
 %>
    <nav class="nxl-navigation">
        <div class="navbar-wrapper">
            <div class="m-header">
                <a href="index.html" class="b-brand">
                    <!-- ========   change your logo hear   ============ -->
                    <img src="assets/images/logo.avif" alt="" class="logo logo-lg" style="width: 50%;"/>
                    <img src="assets/images/logo.avif" alt="" class="logo logo-sm" />
                </a>
            </div>
            <div class="navbar-content">
                <ul class="nxl-navbar">
                    <li class="nxl-item nxl-hasmenu">
                        <a href="dashboard" class="nxl-link">
                            <span class="nxl-micon"><i class="feather-airplay"></i></span>
                            <span class="nxl-mtext">Dashboard</span><span class="nxl-arrow"></span>
                        </a>
                    </li>
                    <%if(log.getUser_type().equalsIgnoreCase("Admin")){ %>
                    <li class="nxl-item nxl-hasmenu">
                        <a href="javascript:void(0);" class="nxl-link">
                            <span class="nxl-micon"><i class="feather-users"></i></span>
                            <span class="nxl-mtext">Employee</span><span class="nxl-arrow"><i class="feather-chevron-right"></i></span>
                        </a>
                        <ul class="nxl-submenu">
                            <li class="nxl-item"><a class="nxl-link" href="manage_employee">Manage Employee</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="employee_rights">Employee Rights</a></li>
                        </ul>
                    </li>
                    <%} %>
                    <li class="nxl-item nxl-hasmenu">
                        <a href="javascript:void(0);" class="nxl-link">
                            <span class="nxl-micon"><i class="feather-cast"></i></span>
                            <span class="nxl-mtext">Client Details</span><span class="nxl-arrow"><i class="feather-chevron-right"></i></span>
                        </a>
                        <ul class="nxl-submenu">
                            <li class="nxl-item"><a class="nxl-link" href="manage_contact">Manage Contact</a></li>
                        </ul>
                    </li>
                    <li class="nxl-item nxl-hasmenu">
                        <a href="javascript:void(0);" class="nxl-link">
                            <span class="nxl-micon"><i class="feather-send"></i></span>
                            <span class="nxl-mtext">Publication</span><span class="nxl-arrow"><i class="feather-chevron-right"></i></span>
                        </a>
                        <ul class="nxl-submenu">
                            <li class="nxl-item"><a class="nxl-link" href="manage_converted_client">Converted Client</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="manage_article_details">Manage Article Details</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="manage_acceptance">Manage Acceptance</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="publication_payment">Manage Payment</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="publication_galley_proof">Galley Proof</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="publication_remaining_payment">Remaining Amount</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="publication_details">Publication Details</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="published_article_details">Published Articles</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="indexed_proper">Indexed Paper</a></li>
                        </ul>
                    </li>
                    <li class="nxl-item nxl-hasmenu">
                        <a href="javascript:void(0);" class="nxl-link">
                            <span class="nxl-micon"><i class="feather-at-sign"></i></span>
                            <span class="nxl-mtext">Writing Paper</span><span class="nxl-arrow"><i class="feather-chevron-right"></i></span>
                        </a>
                        <ul class="nxl-submenu">
                            <li class="nxl-item"><a class="nxl-link" href="writing_converted_client">Converted Client</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="manage_writing_article">Article Details</a></li>
                            <!-- <li class="nxl-item"><a class="nxl-link" href="manage_subject_area">Manage Subject Area</a></li> -->
                            <li class="nxl-item"><a class="nxl-link" href="manage_writing_initial_payment">Initial Payment</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="manage_initial_work">Initial Work</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="writing_paper_confirmation">Confirmation</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="writing_paper_remaining_amount">Remaining Amount</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="writing_paper_full_work">Full Work</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="writing_paper_full_work_confirmation">Full Work Confirmation</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="writing_paper_final_work_details">Final Work Details</a></li>
                        </ul>
                    </li>
                    <li class="nxl-item nxl-hasmenu">
                        <a href="javascript:void(0);" class="nxl-link">
                            <span class="nxl-micon"><i class="feather-dollar-sign"></i></span>
                            <span class="nxl-mtext">Authorship</span><span class="nxl-arrow"><i class="feather-chevron-right"></i></span>
                        </a>
                        <ul class="nxl-submenu">
                        	<li class="nxl-item"><a class="nxl-link" href="authorship_article_details">Article Details</a></li>
                         	<li class="nxl-item"><a class="nxl-link" href="authorship_converted_client">Converted Clients</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="author_details">Author Details</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="authorship_initial_payment">Initial Payment</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="authorship_acceptance">Manage Acceptance</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="authorship_remaining_payment">Remaining Payment</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="authorship_galley_proof">Galley Proof</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="authorship_final_payment">Full Payment</a></li>
                            <li class="nxl-item"><a class="nxl-link" href="authorship_publication_details">Publication Details</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!--! ================================================================ !-->
    <!--! [End]  Navigation Manu !-->
    <!--! ================================================================ !-->