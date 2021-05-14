$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateResearcherForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var t = ($("#hidAppIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "ResearcherAPI",
		type : t,
		data : $("#formResearcher").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onResearcherSaveComplete(response.responseText, status);
		}
	});
});

function onResearcherSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();

			$("#divItemsGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	} else {
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidAppIDSave").val("");
	$("#formResearcher")[0].reset();
}

// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidAppIDSave").val(
					$(this).closest("tr").find('#hidAppIDUpdate').val());
			$("#name").val($(this).closest("tr").find('td:eq(0)').text());
			$("#mobile").val($(this).closest("tr").find('td:eq(1)').text());
			$("#email").val($(this).closest("tr").find('td:eq(2)').text());
			$("#nic").val($(this).closest("tr").find('td:eq(3)').text());
			$("#type").val($(this).closest("tr").find('td:eq(4)').text());
			$("#date").val($(this).closest("tr").find('td:eq(5)').text());

		});

// Remove Operation
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "ResearcherAPI",
		type : "DELETE",
		data : "appID=" + $(this).data("appid"),
		dataType : "text",
		complete : function(response, status) {
			onResearcherDeletedComplete(response.responseText, status);
		}
	});
});

function onResearcherDeletedComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();

			$("#divItemsGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

// CLIENTMODEL
function validateResearcherForm() {
	// NAME
	if ($("#name").val().trim() == "") {
		return "Insert fullName.";

	}

	// MOBILE
	if ($("#mobile").val().trim() == "") {
		return "Insert Mobile.";

	}

	// is numerical value
	var tmpMobile = $("#mobile").val().trim();
	if (!$.isNumeric(tmpMobile)) {
		return "Insert a numerical value for Mobile Number.";

	}

	// Email
	if ($("#email").val().trim() == "") {
		return "Insert Email.";

	}

	// NIC
	if ($("#nic").val().trim() == "") {
		return "Insert NIC.";

	}

	// Address
	if ($("#address").val().trim() == "") {
		return "Insert address.";

	}

	// RserarchType
	if ($("#type").val().trim() == "") {
		return "Insert Rserarch Type .";

	}
	// Date
	if ($("#date").val().trim() == "") {
		return "Insert date.";

	}

	return true;

}
