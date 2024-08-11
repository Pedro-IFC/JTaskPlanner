package cron;

import java.util.Calendar;

/**
 * Classe que representa uma expressão cron e fornece métodos para verificar se uma determinada data corresponde à expressão.
 * A expressão cron é composta por cinco campos: minuto, hora, dia do mês, mês e dia da semana.
 * <p>
 * Exemplo de uso:
 * <pre>
 *     CronExpression cron = new CronExpression("0 12 * * 1");
 *     Calendar date = Calendar.getInstance();
 *     boolean matches = cron.matches(date);
 * </pre>
 * </p>
 */
public class CronExpression {
    private String minute;
    private String hour;
    private String dayOfMonth;
    private String month;
    private String dayOfWeek;

    /**
     * Constrói uma nova instância de {@code CronExpression} a partir de uma expressão cron.
     *
     * @param expression a expressão cron no formato "minuto hora dia-do-mês mês dia-da-semana"
     * @throws IllegalArgumentException se a expressão cron não contiver exatamente 5 campos
     */
    public CronExpression(String expression) {
        String[] fields = expression.split(" ");
        if (fields.length != 5) {
            throw new IllegalArgumentException("Invalid cron expression");
        }
        this.minute = fields[0];
        this.hour = fields[1];
        this.dayOfMonth = fields[2];
        this.month = fields[3];
        this.dayOfWeek = fields[4];
    }
    /**
     * Verifica se a data fornecida corresponde à expressão cron.
     *
     * @param date a data a ser verificada
     * @return {@code true} se a data corresponder à expressão cron, {@code false} caso contrário
     */
    public boolean matches(Calendar date) {
        return matchesField(minute, date.get(Calendar.MINUTE))
            && matchesField(hour, date.get(Calendar.HOUR_OF_DAY))
            && matchesField(dayOfMonth, date.get(Calendar.DAY_OF_MONTH))
            && matchesField(month, date.get(Calendar.MONTH) + 1)
            && matchesField(dayOfWeek, date.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * Verifica se um valor específico corresponde ao campo da expressão cron fornecido.
     *
     * @param field o campo da expressão cron (por exemplo, "1,2,3" ou "*")
     * @param value o valor a ser comparado com o campo
     * @return {@code true} se o valor corresponder ao campo, {@code false} caso contrário
     */
    private boolean matchesField(String field, int value) {
        if (field.equals("*")) {
            return true;
        } else {
            String[] parts = field.split(",");
            for (String part : parts) {
                if (part.equals(String.valueOf(value))) {
                    return true;
                }
            }
            return false;
        }
    }
    public CronExpression convert() {
    	if(!this.month.equals("*")) {
    		this.dayOfMonth=this.dayOfMonth.equals("*")?"1":this.dayOfMonth;
    	}
    	if(!this.dayOfWeek.equals("*")) {
    		this.hour=this.hour.equals("*")?"0":this.hour;
    	}
    	if(!this.dayOfMonth.equals("*")) {
    		this.hour=this.hour.equals("*")?"0":this.hour;
    	}
    	if(!this.hour.equals("*")) {
    		this.minute=this.minute.equals("*")?"0":this.minute;
    	}
    	return this;
    }
}
