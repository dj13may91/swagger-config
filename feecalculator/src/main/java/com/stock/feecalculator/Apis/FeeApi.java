package com.stock.feecalculator.Apis;

import com.stock.feecalculator.Modals.Report;
import com.stock.feecalculator.Modals.Transaction;
import com.stock.feecalculator.ReadFactory.ReadFormatFactory;
import com.stock.feecalculator.ReadFactory.ReadInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FeeApi {
    @Autowired
    ReadFormatFactory readFormatFactory;

    @Autowired
    public ReadInterface csvFile;

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("Application running", HttpStatus.OK);
    }

    @RequestMapping(value = "/readCsv", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> readTransactionsFromCsv() {
        return new ResponseEntity<>(readFormatFactory.readCsv().readTransactionData(), HttpStatus.OK);
    }

    @RequestMapping(value = "/readPdf", method = RequestMethod.GET)
    public ResponseEntity<String> readTransactionsFromPdf() {
        return new ResponseEntity<>(readFormatFactory.readPdf(), HttpStatus.OK);
    }

    @RequestMapping(value = "/readXml", method = RequestMethod.GET)
    public ResponseEntity<String> readTransactionsFromXml() {
        return new ResponseEntity<>(readFormatFactory.readXml(), HttpStatus.OK);
    }

    @RequestMapping(value = "/readTextFile", method = RequestMethod.GET)
    public ResponseEntity<String> readTransactionsFromText() {
        return new ResponseEntity<>(readFormatFactory.readPipeDelimitedTextFile(), HttpStatus.OK);
    }

    @RequestMapping(value = "/readExcel", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<String> readExcel() {
        return new ResponseEntity<>(readFormatFactory.readExcel(), HttpStatus.OK);
    }

    /**
     * keeps data in memory after transaction log file is read.
     * This saves computation time for report fetching
     *
     * @return
     */
    @RequestMapping(value = "/data", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> getData() {
        return new ResponseEntity<>(csvFile.getTransactions(), HttpStatus.OK);
    }

    @RequestMapping(value = "/report", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Report>>> getReport() {
        return new ResponseEntity<>(csvFile.getReportList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/report/{clientId}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getReportForClient(@PathVariable String clientId) {
        List<Report> clientReport = csvFile.getReportList().get(clientId.toUpperCase());
        if (clientReport == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientReport, HttpStatus.OK);
    }

}
