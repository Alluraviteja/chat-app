<div class="main-content">

    <div class="page-content">

        <!-- start page title -->
        <div class="page-title-box">
            <div class="container-fluid">
             <div class="row align-items-center">
                 <div class="page-title">
                     <h4>Chat</h4>
                 </div>
             </div>
            </div>
         </div>
         <!-- end page title -->

        <div class="container-fluid">

            <div class="page-content-wrapper">
				
                <div class="d-lg-flex">
                    <div class="chat-leftsidebar me-lg-4">
                        <div class="card">
                          
                          <div class="p-4">
                            <div class="search-box chat-search-box pb-4">
                                <div class="position-relative">
                                    <input type="text" class="form-control" placeholder="Search with username" id="searchByUsername">
                                   	<i class="mdi mdi-magnify search-icon" id="searchByUsernameButton"></i>
                                </div>
                            </div>

							<div>
								<ul class="list-unstyled chat-list" id="searchByUsernameList">
	                                
	                            </ul>
                            </div/>

                            <div class="chat-leftsidebar-nav">
                                
                                <div class="tab-content py-4">
                                    <div class="tab-pane show active" id="chat">
                                        <div>
                                            <h5 class="font-size-16 mb-3">Contact</h5>
                                            <ul class="list-unstyled chat-list" id="chatFriendsList">
                                                <!-- <li class="active">
                                                    <a href="javascript:void(0)">
                                                        <div class="media">
                                                            <div class="align-self-center me-3">
                                                                <i class="mdi mdi-circle text-success font-size-10"></i>
                                                            </div>
                                                            <div class="avatar-xs align-self-center me-3">
                                                                <span class="avatar-title rounded-circle bg-soft-primary text-primary">
                                                                    S
                                                                </span>
                                                            </div>
                                                            <div class="media-body align-self-center overflow-hidden">
                                                                <h5 class="text-truncate font-size-14 mb-1">Steven Franklin</h5>
                                                            </div>
                                                        </div>
                                                    </a>
                                                </li> -->
                                            </ul>
                                        </div>
                                    </div>  
                                </div>  
								
                            </div>

                          </div>

                        </div>
                    </div>

                    <div class="w-100 user-chat">
                        
                    </div>

                </div>
                <!-- end row -->
            </div>
        </div> <!-- container-fluid -->
    </div>
    <!-- End Page-content -->
</div>


<script src="${pageContext.request.contextPath}/webjars/sockjs-client/sockjs.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/stomp-websocket/stomp.min.js"></script>
<script src="${pageContext.request.contextPath}/functionJs/webSocketsFunction.js"></script>

<script src="${pageContext.request.contextPath}/functionJs/chatFunction.js?v1.0"></script>
