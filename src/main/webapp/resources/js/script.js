function getUsersList() {
	jQuery.ajax({
		cache : false,
		type : "GET",
		url : "getUsersList.ajax",
		dataType : "JSON",
		success : function(data) {
			updateUsersTable(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.responseText);
		}
	});
}

function updateUsersTable(data) {
	var table = new Array();

	table.push("<table class=\"table\">");
	table.push("<tr>");

	table.push("<th>Login</th>");
	table.push("<th>First Name</th>");
	table.push("<th>Last Name</th>");
	table.push("<th>Age</th>");
	table.push("<th>Role</th>");
	table.push("<th>Actions</th>");

	table.push("</tr>");

	for (i in data) {
		var user = data[i];

		table.push("<tr>");

		table.push("<td>");
		table.push(user.login);
		table.push("</td>");

		table.push("<td>");
		table.push(user.firstName);
		table.push("</td>");

		table.push("<td>");
		table.push(user.lastName);
		table.push("</td>");

		table.push("<td>");

		table.push(user.age);
		table.push("</td>");

		table.push("<td>");
		table.push(user.role);
		table.push("</td>");

		table.push("<td>");

		table.push("<a href=\"#\" onclick=\"editUser('" + user.login + "','"
				+ user.password + "','" + user.email + "','" + user.firstName
				+ "','" + user.lastName + "','" + user.birthDate + "','"
				+ user.role + "');\">Edit</a>&nbsp;");

		table.push("<a href=\"#\" onclick=\"deleteUser('" + user.login
				+ "');\">Delete</a>");
		table.push("</tr>");
	}

	jQuery("#allUsersList").empty();
	jQuery("#allUsersList").append(table.join(""));

}

function deleteUser(login) {
	if (confirm("Are you sure?")) {
		deleteUserFromDatabase(login);
	} else {
	}
}

function deleteUserFromDatabase(login) {
	jQuery.ajax({
		type : "POST",
		url : "deleteUser.ajax",
		data : "login=" + login,

		success : function(response) {
			getUsersList();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.responseText);
		}
	});
}

function editUser(login, password, email, firstName, lastName, birthDate, role) {

	$('#formAddEdit input[name="commandName"]').attr('value', "edit");

	$("#allUsersList").slideUp(1000);
	$('#formAddEdit h2').text('Edit user');
	$('#formAddEdit input[name=action]').attr('value', 'editUser');
	$('#errorMess').text('');

	$('#formAddEdit input[name=login]').attr('value', login);
	$('#formAddEdit input[name=password]').attr('value', password);
	$('#formAddEdit input[name=email]').attr('value', email);

	$('#formAddEdit input[name=firstName]').attr('value', firstName);
	$('#formAddEdit input[name=lastName]').attr('value', lastName);
	$('#formAddEdit input[name=birthDate]').attr('value', birthDate);

	$('#formAddEdit input[name=role]').attr('value', role);

	$('#formAddEdit input[name=login]').attr('readOnly', 'true');
	$("#formAddEdit").show(1000);
}

$(document).ready(getUsersList());
$(document).ready(
		function() {

			$("#allUsersList").show();
			$('#formAddEdit').hide();

			$('#addUser')
					.click(
							function(eventObject) {
								eventObject.preventDefault();
								$('#formAddEdit input[name="commandName"]')
										.attr('value', "add");

								$("#allUsersList").slideUp(1000);
								$('#formAddEdit h2').text('Add new user');
								$('#formAddEdit input[name=action]').attr(
										'value', 'addUser');
								$('#errorMess').text('');
								$('#formAddEdit input[name=login]').removeAttr(
										'value');
								$('#formAddEdit input[name=login]').removeAttr(
										'readOnly');

								$('#formAddEdit input[name=password]').attr(
										'value', '');
								$('#formAddEdit input[name=email]').attr(
										'value', '');
								$('#formAddEdit input[name=firstName]').attr(
										'value', '');
								$('#formAddEdit input[name=lastName]').attr(
										'value', '');
								$('#formAddEdit input[name=birthDate]').attr(
										'value', '');

								$("#formAddEdit").show(1000);
							});

			$('#editUser').click(
					function(eventObject) {
						eventObject.preventDefault();
						$('#formAddEdit input[name="commandName"]').attr(
								'value', "edit");

						$("#allUsersList").slideUp(1000);
						$('#formAddEdit h2').text('Edit user');
						$('#formAddEdit input[name=action]').attr('value',
								'editUser');
						$('#errorMess').text('');

						$('#formAddEdit input[name=login]').attr('value',
								$(this).attr('login'));
						$('#formAddEdit input[name=password]').attr('value',
								$(this).attr('password'));
						$('#formAddEdit input[name=email]').attr('value',
								$(this).attr('email'));

						$('#formAddEdit input[name=firstName]').attr('value',
								$(this).attr('firstName'));
						$('#formAddEdit input[name=lastName]').attr('value',
								$(this).attr('lastName'));
						$('#formAddEdit input[name=birthDate]').attr('value',
								$(this).attr('birthDate'));

						$('#formAddEdit input[name=login]').attr('readOnly',
								'true');
						$("#formAddEdit").show(1000);
					});

			$('#cancel').click(function() {
				$("#formAddEdit").hide(1000);
				$("#allUsersList").slideDown(1000);
			});

			$('table tr td #deleteUser').click(
					function(eventObject) {
						eventObject.preventDefault();
						if (confirm('Are you shure?')) {
							$('#allUsersList #userToDel input[name=login]')
									.attr('value', $(this).attr('login'));
							$('#userToDel').submit();
						}
					});

		});
