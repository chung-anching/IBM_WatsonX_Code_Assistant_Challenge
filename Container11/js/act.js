// Assisted by WCA@IBM
// Latest GenAI contribution: ibm/granite-20b-code-instruct-v2
$(document).ready(function() {
    function populateTable(data, tableId) {
        var table = $(tableId + ' tbody');
        table.empty();
        $.each(data, function(index, member) {
            var row = $('<tr>');
            row.append($('<td>').text(member.member_id));
            row.append($('<td>').text(member.name));
            row.append($('<td>').text(member.email));
            row.append($('<td>').text(member.phone));
            row.append($('<td>').text(member.address));
            row.append($('<td>').text(member.join_date));
            table.append(row);
        });
    }

    $('#btn-python').click(function() {
        $.ajax({
            url: 'http://localhost:5000/api/v1/read',
            dataType: 'json',
            success: function(data) {
                populateTable(data, '#table-a');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log('Error: ' + errorThrown);
            }
        });
    });

    $('#btn-java').click(function() {
        $.ajax({
            url: 'http://localhost:8080/my-final/api/data',
            dataType: 'json',
            success: function(data) {
                populateTable(data, '#table-b');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log('Error: ' + errorThrown);
            }
        });
    });
});
