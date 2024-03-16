
<div id="header" class="container-fluid">
    <div class="row align-items-center">
        <div class="col-md-2">
            <h1 class="text-left text-white">Gestione Scuola</h1>
        </div>
        <div class="col-md-9 text-md-end">
            <nav>
                <ul class="list-inline">                    
                    <li class="list-inline-item">
                        <form id="logoutForm" action="${pageContext.request.contextPath}/LoginServletDB" method="post" style="display: inline;">
                            <input type="hidden" name="action" value="logout"> 
                            <a href="#" onclick="document.getElementById('logoutForm').submit();" class="text-white">Esci</a>
                        </form>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
