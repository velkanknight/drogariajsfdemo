//função que verifica se houve erro no preenchimento do campo obrigatorio
//o PF é um comando para chamar componetes do JvaPrimeFace
function verificar(xhr, status, args, dlg, tbl) {
	if (args.validationFailed) {
		PF(dlg).jq.effect("shake", {
			times : 5
		}, 100);

	} else {
		PF(dlg).hide();
		PF(tbl).clearFilters();
	}
}
