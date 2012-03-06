/**
 * 
 */
package org.pikax.log.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tiesebarrell
 */
public abstract class PikaxLogGenerator implements LogGenerator {

    private static final String OUTPUT_FILE_DATE_PATTERN = "yyyy-MM-dd HHmmss.SZ";
    private static final String OUTPUT_FILE_NAME_PATTERN = "generated-log-%s.%s.csv";

    private ExecutorService executorService;

    private File outputLocation;

    @Autowired
    private transient AuditCallback auditCallback;

    /**
     * {@inheritDoc}
     * 
     * @see org.pikax.log.generator.LogGenerator#generate(java.io.File, int)
     */
    public void generate(final File outputLocation, final int amount) {
        this.outputLocation = outputLocation;

        auditCallback.reset();
        auditCallback.start();

        executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < amount; i++) {
            System.out.println("Creating process handler " + i);
            final ProcessHandler processHandler = new ProcessHandler();
            executorService.execute(processHandler);
        }

        executorService.shutdown();

        auditCallback.stop();

        createLog();

    }

    private void createLog() {
        createLogFile(auditCallback.getAuditEntries());
    }

    private void createLogFile(final List<AuditEntry> auditEntries) {

        try {
            Calendar now = Calendar.getInstance();

            final SimpleDateFormat dsf = new SimpleDateFormat(OUTPUT_FILE_DATE_PATTERN);
            final String suffix = dsf.format(now.getTime());

            final File file =
                    new File(outputLocation, String.format(OUTPUT_FILE_NAME_PATTERN, getGeneratorName(), suffix));
            file.createNewFile();

            final OutputStream os = new FileOutputStream(file);

            os.write(AuditEntry.CSV_HEADER_LINE.getBytes());

            for (final AuditEntry auditEntry : auditEntries) {
                os.write(auditEntry.createCSV().getBytes());
            }

            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected abstract void simulateProcess();

    protected abstract String getGeneratorName();

    protected AuditCallback getAuditCallback() {
        return auditCallback;
    }

    private class ProcessHandler implements Runnable {
        public void run() {
            System.out.println("Simulating process");
            simulateProcess();
        }
    }

}
