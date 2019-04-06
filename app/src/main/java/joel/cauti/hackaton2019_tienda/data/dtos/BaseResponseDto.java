package joel.cauti.hackaton2019_tienda.data.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponseDto {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private TokenResponseDto data;
    @SerializedName("error")
    @Expose
    private List<Object> error = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TokenResponseDto getData() {
        return data;
    }

    public void setData(TokenResponseDto data) {
        this.data = data;
    }

    public List<Object> getError() {
        return error;
    }

    public void setError(List<Object> error) {
        this.error = error;
    }
}
