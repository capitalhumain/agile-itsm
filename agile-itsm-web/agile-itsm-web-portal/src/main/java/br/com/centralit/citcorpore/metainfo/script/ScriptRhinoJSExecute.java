package br.com.centralit.citcorpore.metainfo.script;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import br.com.centralit.citcorpore.metainfo.negocio.DinamicViewsService;
import br.com.citframework.service.ServiceLocator;

public class ScriptRhinoJSExecute {

    public Object processScript(final Context cx, final Scriptable scope, final String script, final String sourceName) throws Exception {
        final DinamicViewsService dinamicViewsService = (DinamicViewsService) ServiceLocator.getInstance().getService(DinamicViewsService.class, null);
        final Object language = scope.get("language", scope);
        final String toEvaluateScript = dinamicViewsService.internacionalizaScript(script, language.toString());
        return cx.evaluateString(scope, toEvaluateScript, sourceName, 1, null);
    }

}
