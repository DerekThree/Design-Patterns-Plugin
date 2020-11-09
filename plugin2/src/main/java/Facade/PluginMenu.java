package Facade;

import Builder.Product;
import Factory.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static com.intellij.openapi.ui.Messages.showInputDialog;

// Facade for DePaCoG implemented as a plugin
public class PluginMenu extends AnAction implements Facade {

    Factory factory = new DePaCoG();
    AnActionEvent e;
    Request request;
    ArrayList<String> patternNames;
    Product product;
    PsiFile psiFile;
    psiFile.getViewProvider();

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Configs.logger.trace("Inside PluginMenu.ActionPerformed()");
        this.e = e;
        this.psiFile = e.getData(LangDataKeys.PSI_FILE);
        // update output path, now that we know the current project's directory
        Configs.outDir = e.getProject() == null ? Configs.outDir :
                e.getProject().getBasePath() + File.separator + Configs.outDir;
        Configs.logger.debug("Output directory set to " + Configs.outDir);
        run();
    }

    @Override
    public void run() {
        Configs.logger.trace("Inside PluginMenu.run()");

        patternNames = factory.createReport().getContent();
        request = getRequest();

        // log the request creation
        Configs.logger.debug("PluginMenu created a request:");
        Configs.logger.debug(" Pattern: " + request.pattern);
        Configs.logger.debug(" name1: " + request.name1);
        Configs.logger.debug(" name2: " + request.name2);
        for (ArrayList<String> sArr : request.classNames) {
            Configs.logger.debug("  className: " + sArr.get(0));
            for (String s : sArr)
                Configs.logger.debug("   " + s);
        }

        // order the requested code from Factory.Factory
        product = factory.createProduct(request);

        if (product == null) {
            Messages.showMessageDialog("Something went wrong", "ERROR", Messages.getErrorIcon());
            Configs.logger.error("Null product returned");
        }

        // log file locations
        for (File f : product.getFiles()) {
            Configs.logger.debug(f.getAbsolutePath());
        }

    }

    // builds a request for a pattern based on user input
    private Request getRequest() {
        Configs.logger.trace("Inside PluginMenu.getRequest()");

        Request r = new Request();

        // get pattern number
        String message = new String("Please pick a number:\n\n");
        for (int i=0; i < patternNames.size(); i++)
            message += (i+1) + ". " + patternNames.get(i) + "\n";
        r.pattern = Integer.parseInt(Objects.requireNonNull(showInputDialog(message + "\n", "DePaCoG Plugin Menu", Messages.getQuestionIcon())));

        // get interface names
        r.name1 = showInputDialog("Enter the main interface name", "DePaCoG Plugin Menu", Messages.getQuestionIcon());
        r.name2 = showInputDialog("Enter the second interface name", "DePaCoG Plugin Menu", Messages.getQuestionIcon());

        // get class names
        int n = Integer.parseInt(showInputDialog("Enter the number of classes implementing " + r.name1, "DePaCoG Plugin Menu", Messages.getQuestionIcon()));
        for (int i=0; i<n; i++) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(showInputDialog("Enter a name for class #" + (i+1) + " implementing " + r.name1, "DePaCoG Plugin Menu", Messages.getQuestionIcon()));

            int m = Integer.parseInt(showInputDialog("Enter the number of classes implementing " + r.name2, "DePaCoG Plugin Menu", Messages.getQuestionIcon()));
            for (int j=0; j<m; j++)
                temp.add(showInputDialog("Enter a name for class #" + (j+1) + " implementing " + r.name2, "DePaCoG Plugin Menu", Messages.getQuestionIcon()));
            r.classNames.add(temp);
        }
        return r;
    }
}
