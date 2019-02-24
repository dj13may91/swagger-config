package com.stock.feecalculator;

import com.stock.feecalculator.Apis.FeeApi;
import com.stock.feecalculator.Modals.Report;
import com.stock.feecalculator.Modals.Transaction;
import com.stock.feecalculator.ReadFactory.ReadCsvFile;
import com.stock.feecalculator.ReadFactory.ReadFormatFactory;
import com.stock.feecalculator.ReadFactory.ReadInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FeeApiTest {

    @Mock
    ReadFormatFactory formatFactory;

    @Mock
    ReadInterface csvFile;

    @InjectMocks
    FeeApi feeApi;

    @Test
    public void transactionDataValidation() {
        when(formatFactory.readCsv()).thenReturn(csvFile);
        ResponseEntity<List<Transaction>> responseEntity = new ResponseEntity<>(formatFactory.readCsv().getTransactions(), HttpStatus.OK);
        assertEquals(responseEntity, feeApi.readTransactionsFromCsv());
    }

    @Test
    public void getReport() {
        when(formatFactory.readCsv()).thenReturn(csvFile);
        ResponseEntity<Map<String, List<Report>>> responseEntity =
                new ResponseEntity<>(formatFactory.readCsv().getReportList(), HttpStatus.OK);
        assertEquals(responseEntity, feeApi.getReport());
    }

    @Test
    public void getReportForInValidClient() {
        String clientId = "AS";
        ResponseEntity<List<Report>> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        assertEquals(responseEntity, feeApi.getReportForClient(clientId));
    }

    @Test
    public void getReportForValidClient() {
        String clientId = "AS";
        csvFile = new ReadCsvFile();
        csvFile.setTRANSACTION_FILE_PATH(ReadFormatFactory.TRANSACTION_FILE_PATH);
        Map<String, List<Report>> reportMap = csvFile.getReportList();
        ResponseEntity<List<Report>> responseEntity = new ResponseEntity<>(reportMap.get(clientId), HttpStatus.OK);
        when(feeApi.csvFile.getReportList()).thenReturn(reportMap);
        assertEquals(responseEntity, feeApi.getReportForClient(clientId));
    }
}
