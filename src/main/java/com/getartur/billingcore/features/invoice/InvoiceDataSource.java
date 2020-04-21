package com.getartur.billingcore.features.invoice;

import com.getartur.billingcore.shared.config.CompanyProperties;
import com.getartur.billingcore.shared.domain.entities.customer.Customer;
import com.getartur.billingcore.shared.domain.entities.invoice.Invoice;
import com.getartur.billingcore.shared.domain.entities.invoice.InvoiceRow;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class InvoiceDataSource implements JRDataSource {

    private final CompanyProperties companyProperties;
    private final Invoice invoice;
    private final Customer customer;
    private final List<InvoiceRow> rows;
    private int index = -2;
    private final BigDecimal subtotal;
    private final BigDecimal vatForSubtotal;
    private final BigDecimal totalWithVat;

    private final DecimalFormat df = new DecimalFormat(
            "#,##0.00",
            new DecimalFormatSymbols(Locale.GERMANY)
    );

    public InvoiceDataSource(Invoice invoice, Customer customer, List<InvoiceRow> invoiceRows, CompanyProperties companyProperties) {
        this.invoice = invoice;
        this.customer = customer;
        this.companyProperties = companyProperties;
        /*start = LocalDate.of(year, month, 1);
        end = start.plusMonths(1).minusDays(1);*/
        this.rows = invoiceRows;

        BigDecimal subtotal = BigDecimal.ZERO;
        for(InvoiceRow row : rows) {
            subtotal = subtotal.add(row.getAmount());
        }
        this.subtotal = subtotal.setScale(2, RoundingMode.HALF_UP);;
        this.vatForSubtotal = subtotal.multiply(new BigDecimal(0.2)).setScale(2, RoundingMode.HALF_UP);
        this.totalWithVat = subtotal.add(vatForSubtotal).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean next() {
        return ++index < rows.size();
    }

    @Override
    public Object getFieldValue(JRField field) throws JRException {
        if(field.getName().equals("title")) {
            return rows.get(index).getProjectName();
        }
        if(field.getName().equals("count")) {
            return formatDecimal(rows.get(index).getCount().setScale(2, RoundingMode.HALF_UP), ("HOUR".equals(rows.get(index).getRateType()) ? "h" : "Stk"));
        }
        if(field.getName().equals("rate")) {
            return formatDecimal(rows.get(index).getRate().setScale(2, RoundingMode.HALF_UP), "€");
        }
        if(field.getName().equals("vatRate")) {
            return rows.get(index).getVatRate().setScale(0, RoundingMode.HALF_UP).toPlainString() + "%";
        }
        if(field.getName().equals("amount")) {
            return formatDecimal(rows.get(index).getAmount().setScale(2, RoundingMode.HALF_UP), "€");
        }
        if(field.getName().equals("subtotal")) {
            return formatDecimal(subtotal, "€");
        }
        if(field.getName().equals("vatOfText")) {
            return "USt. 20% von " + formatDecimal(subtotal, "€");
        }
        if(field.getName().equals("vatForSubtotal")) {
            return formatDecimal(vatForSubtotal, "€");
        }
        if(field.getName().equals("total")) {
            return formatDecimal(totalWithVat, "€");
        }
        if(field.getName().equals("nameHeader")) {
            return companyProperties.getOwner();
        }
        if(field.getName().equals("addressHeader")) {
            return companyProperties.getAddress().getLine1() + "\n"
                    + (companyProperties.getAddress().getLine2() != null && companyProperties.getAddress().getLine2().length() > 0 ? companyProperties.getAddress().getLine2() + "\n" : "")
                    + companyProperties.getAddress().getZip() + " " + companyProperties.getAddress().getCity() + "\n"
                    + companyProperties.getAddress().getCountry();
        }
        if(field.getName().equals("bankingFooter")) {
            return companyProperties.getBankAccount().getName() + "\n"
                    + companyProperties.getOwner() + "\n"
                    + companyProperties.getBankAccount().getIban() + "\n"
                    + companyProperties.getBankAccount().getBic() + "\n\n"
                    + companyProperties.getVatin();
        }
        if(field.getName().equals("communicationFooter")) {
            return companyProperties.getEMail() + "\n"
                    + companyProperties.getMobile();
        }
        if(field.getName().equals("customerHeader")) {
            return customer.getName() + "\n" +
                    // TODO: address
                    "" + "\n"
                    + "" + "\n"
                    + "" + "\n\n"
                    + "(UID: " + customer.getVatin() + ")";
        }
        if(field.getName().equals("invoiceHeader")) {
            return invoice.getNumber() + "\n"
                    + "2001" + "\n"
                    + invoice.getIssued().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n"
                    + invoice.getDelivery();
        }
        if(field.getName().equals("invoicePlaceDate")) {
            return companyProperties.getAddress().getCity() + ", " + invoice.getIssued().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        if(field.getName().equals("invoiceTitle")) {
            return "Rechnung " + invoice.getNumber();
        }
        if(field.getName().equals("intro")) {
            return invoice.getIntro();
        }
        if(field.getName().equals("outro")) {
            return invoice.getOutro();
        }
        return null;
    }

    private String formatDecimal(BigDecimal dec, String postFix) {
        return df.format(dec.doubleValue()) + ((postFix != null) ? " " + postFix : "");
    }

}
