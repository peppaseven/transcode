var startGooleApp = function() {
  		console.log("loading goolge oauth api");
	    gapi.load('auth2', function(){
	      // Retrieve the singleton for the GoogleAuth library and set up the client.
	      auth2 = gapi.auth2.init({
	        client_id: '230759844594-8qsm65qd0fln0snpon0vjmjjlcubavaa.apps.googleusercontent.com',
	        cookiepolicy: 'single_host_origin',
	        // Request scopes in addition to 'profile' and 'email'
	        //scope: 'additional_scope'
	      });
	      attachSignin(document.getElementById('glbtn'));
	    });
	  };
startGooleApp();