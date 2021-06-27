package br.com.comicszup.client;

import br.com.comicszup.dto.response.ComicsResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="marvel", url = "https://gateway.marvel.com:443/v1/public")
public interface ComicsFeing {
    @GetMapping("/comics")
    public ComicsResponseDTO getAll(@RequestParam("ts") Long ts, @RequestParam("apikey") String api_key, @RequestParam("hash") String hash);
}
