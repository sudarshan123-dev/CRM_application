<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Date Selection Restriction</title>
</head>
<body>
    <form method="post" action="process.jsp">
        <label for="startDate">Select Start Date:</label>
        <input type="date" id="startDate" name="startDate" required><br><br>
        
        <label for="endDate">Select End Date:</label>
        <input type="date" id="endDate" name="endDate" required>
        
        <br><br>
        <input type="submit" value="Submit">
    </form>

    <script>
        const startDateInput = document.getElementById('startDate');
        const endDateInput = document.getElementById('endDate');

        startDateInput.addEventListener('change', function() {
            // Set minimum value of endDateInput to the value of startDateInput
            const selectedStartDate = new Date(this.value);
            const formattedDate = selectedStartDate.toISOString().split('T')[0];
            endDateInput.min = formattedDate;
        });

        endDateInput.addEventListener('change', function() {
            if (new Date(this.value) < new Date(startDateInput.value)) {
                alert('End date cannot be before start date.');
                this.value = ''; // Reset the invalid end date
            }
        });
    </script>
</body>
</html>
    