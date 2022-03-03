function validateForm() {
	const username = document.forms["userForm"]["username"].value;
	const password = document.forms["userForm"]["password"].value;

	if (username == "") {
		alert("Username must be filled out");
		return false;
	}

	if (password == "") {
		alert("Password must be filled out");
		return false;
	}
}