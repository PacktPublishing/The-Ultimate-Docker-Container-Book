using System.Text.Json;

namespace library_component_tests;

public record Species(int id, string name, string description);

public class UnitTest1
{
    [Fact]
    public async Task can_add_species()
    {
        // Arrange
        var client = new HttpClient();
        var url = "http://localhost:8080/species";
        var species = new Species(1, "Elephant", "The big gray mammal");
        var json = JsonSerializer.Serialize(species);
        var body = new StringContent(json, System.Text.Encoding.UTF8, "application/json");

        // act
        var response = await client.PostAsync(url, body);

        // assert
        Assert.Equal(System.Net.HttpStatusCode.OK, response.StatusCode);
    }

    [Fact]
    public async Task can_get_a_species_by_id()
    {
        // Arrange
        var client = new HttpClient();
        var species = new Species(2, "Dog", "Human's best friend");
        var json = JsonSerializer.Serialize(species);
        var body = new StringContent(json, System.Text.Encoding.UTF8, "application/json");
        var res = await client.PostAsync("http://localhost:8080/species", body);
        
        // Act
        var response = await client.GetAsync("http://localhost:8080/species/2");
        
        // Assert
        var stream = await response.Content.ReadAsStreamAsync();
        var data = await JsonSerializer.DeserializeAsync<Species>(stream);
        System.Console.WriteLine(data);
        Assert.NotNull(data);
        Assert.Equal(species, data);
    }
}