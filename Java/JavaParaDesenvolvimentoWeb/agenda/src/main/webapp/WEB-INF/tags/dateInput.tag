<%@ attribute name="id" required="true" %>
<%@ attribute name="value" %>

<input type="text" id="${id}" name="${id}" value="${value}" />
<script>
    $("#${id}").datepicker({
        dateFormat: 'dd/mm/yy',
        changeMonth: true,
        changeYear: true
        });
</script>