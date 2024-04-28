function editUser(button) {
	var userId = $(button).data('user-id');

	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/api/v1/admin/users/' + userId,
		success: function(user) {
			// Populate the modal form with user details
			$('#userId').val(user.id);
			$('#emailEdit').val(user.email);
			$('#fullNameEdit').val(user.fullName);
			$('#phoneNumberEdit').val(user.phoneNumber);
			$('#genderEdit').val(user.gender);
			$('#addressEdit').val(user.address);
			// Show the modal
			$('#editUserModal').modal('show');
		},
		error: function(error) {
			console.log('Error fetching user details:', error);
		}
	});
}


// Function to populate modal with user details
function populateModal(user) {
	// Populate your modal form fields with user details
	// ...

	// Handle form submission
	$('#editUserModal').submit(function(event) {
		event.preventDefault();
		// Make Ajax request to update user details
		$.ajax({
			type: 'PUT',
			url: ctx + '"/api/v1/admin/users/"' + user.id,
			contentType: 'application/json',
			data: JSON.stringify({
				// Populate the updated user details from the form fields
				// ...
			}),
			success: function(updatedUser) {
				// Handle success (e.g., update the table with the new user details)
				// ...
				// Hide the modal
				$('#updateUserModal').modal('hide');
			},
			error: function(error) {
				console.log('Error updating user details:', error);
			}
		});
	});
}